package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.manufacturer.ManufacturerService;
import mk.ukim.finki.db.distributorapp.manufacturer.dto.ManufacturerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manufacturer")
@CrossOrigin(origins = "*")
public class RestManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping("/all")
    public ResponseEntity<List<ManufacturerDto>> getAllManufacturers() {
        List<ManufacturerDto> manufacturers = this.manufacturerService.getAllManufacturers();
        return ResponseEntity.ok().body(manufacturers);
    }
}
