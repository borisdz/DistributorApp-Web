package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Order;
import mk.ukim.finki.db.distributorapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAll(){
        return this.orderService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return this.orderService.findById(id)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Order> save(@RequestParam Long customerId, @RequestParam List<Article> articles){
        return this.orderService.save(customerId,articles)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        if(this.orderService.exists(id)){
            this.orderService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
