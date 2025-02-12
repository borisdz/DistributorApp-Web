package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.city.dto.CityDto;
import mk.ukim.finki.db.distributorapp.city.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/city")
public class RestCityController {
    private final CityService cityService;

    public RestCityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = this.cityService.listCities();
        return ResponseEntity.ok(cities);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addCity(@RequestBody CityDto CityDto) {
        Integer result = this.cityService.create(CityDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editCity(@RequestBody CityDto CityDto) {
        Integer result = this.cityService.edit(CityDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id) {
        this.cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
