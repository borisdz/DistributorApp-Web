package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.OrdersDto;
import mk.ukim.finki.db.distributorapp.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {
    private final OrdersService OrderService;

    public OrdersController(OrdersService orderService) {
        OrderService = orderService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<OrdersDto> orders = OrderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addOrders(@RequestBody OrdersDto OrdersDto) {
        Integer result = this.OrderService.create(OrdersDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editOrders(@RequestBody OrdersDto OrdersDto) {
        Integer result =this.OrderService.edit(OrdersDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long id) {
        this.OrderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
