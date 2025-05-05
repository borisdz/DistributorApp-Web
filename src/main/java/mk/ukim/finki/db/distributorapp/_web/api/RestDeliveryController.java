package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryWithOrdersDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.OrdersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RestDeliveryController {
    private final DeliveryService deliveryService;
    private final OrdersService ordersService;

    @GetMapping("/delivery-with-orders/{deliveryId}")
    @PreAuthorize("hasAnyRole('DRIVER','ADMIN')")
    public ResponseEntity<DeliveryWithOrdersDto> getDeliveryWithOrder(@PathVariable Long deliveryId) {
        DeliveryWithOrdersDto result = new DeliveryWithOrdersDto();
        DeliveryFullDto delivery = this.deliveryService.findDeliveryById(deliveryId);
        result.setDelivery(delivery);
        List<OrdersDto> deliveryOrders = this.ordersService.findOrdersByDelivery(deliveryId);
        result.setOrders(deliveryOrders);
        return ResponseEntity.ok(result);
    }
}
