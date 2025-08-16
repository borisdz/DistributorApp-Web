package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "*")
public class RestWarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping("/find-by-city")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<WarehouseDto> findWarehouseByCity(@RequestParam Integer cityId) {
        WarehouseDto wh = this.warehouseService.findByCityId(cityId);
        return ResponseEntity.ok().body(wh);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<WarehouseDto>> findAll() {
        List<WarehouseDto> result = this.warehouseService.findAll();
        return ResponseEntity.ok().body(result);
    }
}
