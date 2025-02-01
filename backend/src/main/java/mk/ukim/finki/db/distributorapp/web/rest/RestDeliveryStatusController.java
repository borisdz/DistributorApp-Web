package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryStatusDto;
import mk.ukim.finki.db.distributorapp.service.DeliveryStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/dStatus")
public class RestDeliveryStatusController {
    private final DeliveryStatusService deliveryStatusService;

    public RestDeliveryStatusController(DeliveryStatusService deliveryStatusService) {
        this.deliveryStatusService = deliveryStatusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DeliveryStatusDto>> getAllDeliveryStatus() {
        List<DeliveryStatusDto> statuses = this.deliveryStatusService.getAllDeliveryStatus();
        return ResponseEntity.ok(statuses);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addDeliveryStatus(@RequestBody DeliveryStatusDto DeliveryStatusDto) {
        Integer result = this.deliveryStatusService.create(DeliveryStatusDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editDeliveryStatus(@RequestBody DeliveryStatusDto DeliveryStatusDto) {
        Integer result = this.deliveryStatusService.edit(DeliveryStatusDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDeliveryStatus(@PathVariable Short id) {
        this.deliveryStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
