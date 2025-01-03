package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.CityDto;
import mk.ukim.finki.db.distributorapp.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
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
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        this.cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
