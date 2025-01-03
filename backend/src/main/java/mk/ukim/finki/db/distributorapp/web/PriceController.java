package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.PriceDto;
import mk.ukim.finki.db.distributorapp.service.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriceDto>> getAllPrices() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<PriceDto> addPrice(@RequestBody PriceDto PriceDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<PriceDto> editPrice(@RequestBody PriceDto PriceDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PriceDto> deletePrice(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
