package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.ArticleService;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.articleUnit.ArticleUnitService;
import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitSimpleDto;
import mk.ukim.finki.db.distributorapp.customer.CustomerService;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.CreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderWithItemsDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto order) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getPrincipal().toString();

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

    @GetMapping("/{orderId}")
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
}