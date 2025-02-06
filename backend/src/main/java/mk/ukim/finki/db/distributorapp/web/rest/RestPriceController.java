package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.dto.PriceDto;
import mk.ukim.finki.db.distributorapp.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/price")
public class RestPriceController {
    private final PriceService priceService;

    public RestPriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriceDto>> getAllPrices() {
        List<PriceDto> prices = this.priceService.getAllPrices();
        return ResponseEntity.ok(prices);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addPrice(@RequestBody PriceDto PriceDto) {
        Integer result = this.priceService.create(PriceDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editPrice(@RequestBody PriceDto PriceDto) {
        Integer result = this.priceService.edit(PriceDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Integer id) {
        this.priceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
