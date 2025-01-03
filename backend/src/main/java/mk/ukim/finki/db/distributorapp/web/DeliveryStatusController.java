package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryStatusDto;
import mk.ukim.finki.db.distributorapp.service.DeliveryStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dStatus")
public class DeliveryStatusController {
    private final DeliveryStatusService deliveryStatusService;

    public DeliveryStatusController(DeliveryStatusService deliveryStatusService) {
        this.deliveryStatusService = deliveryStatusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DeliveryStatusDto>> getAllDeliveryStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<DeliveryStatusDto> addDeliveryStatus(@RequestBody DeliveryStatusDto DeliveryStatusDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<DeliveryStatusDto> editDeliveryStatus(@RequestBody DeliveryStatusDto DeliveryStatusDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeliveryStatusDto> deleteDeliveryStatus(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
