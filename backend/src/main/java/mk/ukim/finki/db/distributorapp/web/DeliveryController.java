package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DeliveryDto>> getAllDeliveries() {
        List<DeliveryDto> deliveries = deliveryService.getAllDeliveries();
        return ResponseEntity.ok(deliveries);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addDelivery(@RequestBody DeliveryDto DeliveryDto) {
        Integer result = this.deliveryService.create(DeliveryDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editDelivery(@RequestBody DeliveryDto DeliveryDto) {
        Integer result = this.deliveryService.edit(DeliveryDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        this.deliveryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
