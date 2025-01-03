package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
        List<DriverDto> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addDriver(@RequestBody DriverDto driverDto) {
        Integer result = this.driverService.create(driverDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editDriver(@RequestBody DriverDto driverDto) {
        Integer result = this.driverService.edit(driverDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        this.driverService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
