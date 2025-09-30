package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseStockDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "*")
public class RestWarehouseController {
    private final WarehouseService warehouseService;
    private final UserService userService;

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

    @GetMapping("/manager/stock")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<List<WarehouseStockDto>> getWarehouseStock(Principal principal){
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        WarehouseDto wh = this.warehouseService.findByUserId(user.getCityId());

        List<WarehouseStockDto> stock = this.warehouseService.getWarehouseStock(wh.getId());
        return ResponseEntity.ok().body(stock);
    }
}
