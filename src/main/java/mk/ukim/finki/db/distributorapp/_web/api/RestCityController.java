package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.city.CityService;
import mk.ukim.finki.db.distributorapp.city.dto.CityDtoRegister;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
@CrossOrigin(origins = "*")
public class RestCityController {
    private final CityService cityService;
    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<CityDtoRegister>> getAll() {
        List<CityDtoRegister> cities = this.cityService.findAllCityDtos();
        return ResponseEntity.ok().body(cities);
    }

    @GetMapping("/manager/available-cities")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<List<CityDtoRegister>> getAvailableCitiesForManager(Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        List<CityDtoRegister> cities = this.cityService.findCitiesForManager(user.getId());
        return ResponseEntity.ok().body(cities);
    }
}
