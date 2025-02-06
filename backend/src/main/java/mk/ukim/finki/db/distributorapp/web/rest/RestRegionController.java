package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.dto.RegionDto;
import mk.ukim.finki.db.distributorapp.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/region")
public class RestRegionController {
    private final RegionService regionService;

    public RestRegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionDto>> getAllRegions() {
        List<RegionDto> regions = this.regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addRegion(@RequestBody RegionDto RegionDto) {
        Integer result = this.regionService.create(RegionDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editRegion(@RequestBody RegionDto RegionDto) {
        Integer result = this.regionService.edit(RegionDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id) {
        this.regionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
