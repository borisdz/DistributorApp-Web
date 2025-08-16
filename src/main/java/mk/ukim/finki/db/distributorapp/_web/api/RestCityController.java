package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.city.CityService;
import mk.ukim.finki.db.distributorapp.city.dto.CityDtoRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
@CrossOrigin(origins = "*")
public class RestCityController {
    private final CityService cityService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<CityDtoRegister>> getAll() {
        List<CityDtoRegister> cities = this.cityService.findAllCityDtos();
        return ResponseEntity.ok().body(cities);
    }
}
