package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.dto.ManufacturerDto;
import mk.ukim.finki.db.distributorapp.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/manufacturer")
public class RestManufacturerController {
    private final ManufacturerService manufacturerService;

    public RestManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ManufacturerDto>> getAllManufacturers() {
        List<ManufacturerDto> manufacturers = manufacturerService.getAllManufacturers();
        return ResponseEntity.ok(manufacturers);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addManufacturer(@RequestBody ManufacturerDto ManufacturerDto) {
        Integer result = this.manufacturerService.create(ManufacturerDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editManufacturer(@RequestBody ManufacturerDto ManufacturerDto) {
        Integer result = this.manufacturerService.edit(ManufacturerDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        this.manufacturerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
