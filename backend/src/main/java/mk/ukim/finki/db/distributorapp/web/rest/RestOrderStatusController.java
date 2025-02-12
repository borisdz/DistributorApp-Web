package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.orders.dto.OrderStatusDto;
import mk.ukim.finki.db.distributorapp.orders.OrderStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/oStatus")
public class RestOrderStatusController {
    private final OrderStatusService orderStatusService;

    public RestOrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderStatusDto>> getAllOrderStatus() {
        List<OrderStatusDto> statuses = this.orderStatusService.getAllOrderStatus();
        return ResponseEntity.ok(statuses);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addOrderStatus(@RequestBody OrderStatusDto OrderStatusDto) {
        Integer result = this.orderStatusService.create(OrderStatusDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editOrderStatus(@RequestBody OrderStatusDto OrderStatusDto) {
        Integer result = this.orderStatusService.edit(OrderStatusDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable Short id) {
        this.orderStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
