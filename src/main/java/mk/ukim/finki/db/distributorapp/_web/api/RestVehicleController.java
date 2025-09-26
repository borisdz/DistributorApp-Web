package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.vehicle.VehicleService;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = "*")
public class RestVehicleController {
    private final VehicleService vehicleService;
    private final WarehouseService warehouseService;
    private final UserService userService;

    @GetMapping("/find-by-city")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<VehicleBasicDto>> getVehiclesByCity(@RequestParam Integer cityId) {
        WarehouseDto wh = this.warehouseService.findByCityId(cityId);
        List<VehicleBasicDto> vehiclesByCity = this.vehicleService.getVehiclesByWarehouse(wh.getId());
        return ResponseEntity.ok().body(vehiclesByCity);
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<VehicleDto> addNewVehicle(@RequestBody VehicleBasicDto vehicleBasicDto) {
        return null;
    }

    @GetMapping("/manager/vehicles")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<List<VehicleBasicDto>> getManagerVehicles(Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        List<VehicleBasicDto> vehicles = this.vehicleService.getBasicVehiclesByManagerId(user.getId());
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/manager/vehicles/available-vehicles")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<?> getManagerAvailableVehicles(
            Principal principal,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        if(user==null){
            return ResponseEntity.badRequest().body("User not found");
        }

        List<VehicleDto> vehicles;

        if(date!=null){
            vehicles = this.vehicleService.getAvailableVehiclesForDateByManager(user.getId(), date);
        }else{
            // TODO: Implement with proper method and DTO
            vehicles = this.vehicleService.getVehiclesByManager(user.getId());
        }

        return ResponseEntity.ok(vehicles);
    }
}
