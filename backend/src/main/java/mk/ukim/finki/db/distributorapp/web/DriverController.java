package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.DriverDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @GetMapping("/all")
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<DriverDto> addDriver(@RequestBody DriverDto driverDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<DriverDto> editDriver(@RequestBody DriverDto driverDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DriverDto> deleteDriver(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
