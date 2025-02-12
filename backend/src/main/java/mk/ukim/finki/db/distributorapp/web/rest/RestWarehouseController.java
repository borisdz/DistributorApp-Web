package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/warehouse")
public class RestWarehouseController {
    private final WarehouseService warehouseService;

    public RestWarehouseController(WarehouseService warehouseService) {
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
