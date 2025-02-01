package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/vehicle")
public class RestVehicleController {
    private final VehicleService vehicleService;

    public RestVehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addVehicle(@RequestBody VehicleDto VehicleDto) {
        Integer result = this.vehicleService.create(VehicleDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editVehicle(@RequestBody VehicleDto VehicleDto) {
        Integer result = this.vehicleService.edit(VehicleDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        this.vehicleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
