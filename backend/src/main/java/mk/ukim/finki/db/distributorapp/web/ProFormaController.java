package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaDto;
import mk.ukim.finki.db.distributorapp.service.ProFormaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pForma")
public class ProFormaController {
    private final ProFormaService proFormaService;

    public ProFormaController(ProFormaService proFormaService) {
        this.proFormaService = proFormaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProFormaDto>> getAllProFormas() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ProFormaDto> addProForma(@RequestBody ProFormaDto ProFormaDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ProFormaDto> editProForma(@RequestBody ProFormaDto ProFormaDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProFormaDto> deleteProForma(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
