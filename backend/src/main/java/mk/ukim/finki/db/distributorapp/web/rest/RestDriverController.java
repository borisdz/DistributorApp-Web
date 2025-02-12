package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.driver.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/driver")
public class RestDriverController {
    private final DriverService driverService;

    public RestDriverController(DriverService driverService) {
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
