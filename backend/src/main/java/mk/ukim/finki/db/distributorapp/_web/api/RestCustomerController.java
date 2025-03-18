package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.ArticleService;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.articleUnit.ArticleUnitService;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDashboardDto;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.AndroidCreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.CreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.proForma.ProFormaService;
import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
@CrossOrigin(origins = "*")
public class RestCustomerController {
    private final ArticleService articleService;
    private final OrdersService ordersService;
    private final DeliveryService deliveryService;
    private final ProFormaService proFormaService;
    private final WarehouseService warehouseService;
    private final UserService userService;
    private final ArticleUnitService articleUnitService;

    @GetMapping("/{customerId}/dashboard")
    public ResponseEntity<CustomerDashboardDto> getCustomerDashboard(@PathVariable("customerId") Long customerId) {
        CustomerDashboardDto dashboard = new CustomerDashboardDto();
        List<OrderSimpleDto> userOrders = this.ordersService.findSimpleOrdersByCustomer(customerId);
        List<DeliveryDto> userDeliveries = this.deliveryService.getCurrentDeliveriesByCustomer(customerId);
        List<ProFormaDto> userProFormas = this.proFormaService.getCurentProFormasByCustomer(customerId);
        dashboard.setOrders(userOrders);
        dashboard.setDeliveries(userDeliveries);
        dashboard.setProFormas(userProFormas);
        return ResponseEntity.ok(dashboard);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getArticles() {
        List<ArticleDto> articles = this.articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }

    @PostMapping("/create-order")
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
