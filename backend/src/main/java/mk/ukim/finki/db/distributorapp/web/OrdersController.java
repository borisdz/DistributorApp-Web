package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.OrdersDto;
import mk.ukim.finki.db.distributorapp.service.OrdersService;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<OrdersDto> addOrders(@RequestBody OrdersDto OrdersDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<OrdersDto> editOrders(@RequestBody OrdersDto OrdersDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrdersDto> deleteOrders(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
