package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WarehouseDto>> getAllWarehouses() {
        List<WarehouseDto> warehouses = this.warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addWarehouse(@RequestBody WarehouseDto WarehouseDto) {
        Integer result = this.warehouseService.create(WarehouseDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editWarehouse(@RequestBody WarehouseDto WarehouseDto) {
        Integer result = this.warehouseService.edit(WarehouseDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Integer id) {
        this.warehouseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
