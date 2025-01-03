package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.OrderStatusDto;
import mk.ukim.finki.db.distributorapp.service.OrderStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oStatus")
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderStatusDto>> getAllOrderStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<OrderStatusDto> addOrderStatus(@RequestBody OrderStatusDto OrderStatusDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<OrderStatusDto> editOrderStatus(@RequestBody OrderStatusDto OrderStatusDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrderStatusDto> deleteOrderStatus(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
