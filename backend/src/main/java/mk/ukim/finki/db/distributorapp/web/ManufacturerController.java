package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ManufacturerDto;
import mk.ukim.finki.db.distributorapp.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ManufacturerDto>> getAllManufacturers() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ManufacturerDto> addManufacturer(@RequestBody ManufacturerDto ManufacturerDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ManufacturerDto> editManufacturer(@RequestBody ManufacturerDto ManufacturerDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManufacturerDto> deleteManufacturer(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
