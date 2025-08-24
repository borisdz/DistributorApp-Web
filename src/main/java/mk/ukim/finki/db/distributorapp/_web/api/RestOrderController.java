package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.ArticleService;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.articleUnit.ArticleUnitService;
import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitSimpleDto;
import mk.ukim.finki.db.distributorapp.customer.CustomerService;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.AndroidCreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.CreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderWithItemsDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class RestOrderController {
    private final OrdersService ordersService;
    private final UserService userService;
    private final CustomerService customerService;
    private final WarehouseService warehouseService;
    private final ArticleUnitService articleUnitService;
    private final ArticleService articleService;

    // ------------------- WEB -------------------
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto order, Principal principal) {

        String userEmail = principal.getName();

        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        this.ordersService.create(order,userEmail);

        CustomerDto customer = this.customerService.findCustomerById(user.getId());

        List<OrderSimpleDto> simpleCustomerOrders = this.ordersService.findSimpleOrdersByCustomer(customer.getId());
        OrderSimpleDto createdOrder = simpleCustomerOrders.get(simpleCustomerOrders.size()-1);
        WarehouseDto wh = this.warehouseService.findByCityId(user.getCityId());

        List<ArticleUnitSimpleDto> editedUnits = this.articleUnitService.addArticleUnitToOrder(order.getOrderItems(), createdOrder.getId(), wh.getId());

        for (ArticleUnitSimpleDto unit : editedUnits) {
            this.articleUnitService.simpleEdit(unit);
        }

        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/customer/{orderId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        // TODO: In the JSON result add a list of article DTOs of the articles that are in that order.
        OrderSimpleDto order = this.ordersService.findSimpleOrderById(orderId);
        OrderWithItemsDto result = new OrderWithItemsDto();
        result.setOrder(order);
        List<ArticleDto> orderArticles = this.articleService.getArticlesByOrder(orderId);
        result.setItems(orderArticles);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer/current-orders")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<List<OrderSimpleDto>> getCurrentOrders(Principal principal) {
        String email = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(email);
        List<OrderSimpleDto> result = this.ordersService.findSimpleOrdersByCustomer(user.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/manager/unassigned-orders")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<List<OrderSimpleDto>> getManagerUnassignedOrders(Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        List<OrderSimpleDto> newOrders = this.ordersService.getNewOrdersByManager(user.getId());
        return ResponseEntity.ok(newOrders);
    }

    // ------------------- MOBILE ANDROID ------------------
    @PostMapping("/mobile/customer/create-order")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<OrderSimpleDto> createOrder(@RequestBody AndroidCreateOrderDto order){
        // todo: implement creating order via api and try to unify the code for both android and angular.
        UserDto user = this.userService.findUserDtoByEmail(order.getUserEmail());
        WarehouseDto wh = this.warehouseService.findByUserId(user.getCityId());
        ArticleDto article = this.articleService.findById(order.getArticleId(), wh.getId());
        CreateOrderDto orderDto = new CreateOrderDto();
        orderDto.setProForma(order.getProForma());
        return ResponseEntity.ok(new OrderSimpleDto());
    }

}