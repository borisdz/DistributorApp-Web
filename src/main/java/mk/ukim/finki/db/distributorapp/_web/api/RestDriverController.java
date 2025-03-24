package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.driver.DriverService;
import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/driver")
@CrossOrigin(origins = "*")
public class RestDriverController {

    private final UserService userService;
    private final DriverService driverService;

    @GetMapping("/newDeliveries")
    public ResponseEntity<?> getNewDeliveries(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getPrincipal().toString();

        UserDto user = this.userService.findUserDtoByEmail(email);

        if(user == null){
            return ResponseEntity.badRequest().body("User not found");
        }

        DriverDto driver = this.driverService.findById(user.getId());
        if(driver == null){
            return ResponseEntity.badRequest().body("Driver not found");
        }

        List<DeliverySimpleDto> deliveries = this.driverService.getNewAssignedDeliveries(driver.getId());

        return ResponseEntity.ok().body(deliveries);
    }
}
