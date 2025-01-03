package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaDto;
import mk.ukim.finki.db.distributorapp.service.ProFormaService;
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
        List<ProFormaDto> proFormas = this.proFormaService.getAllProForma();
        return ResponseEntity.ok(proFormas);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addProForma(@RequestBody ProFormaDto ProFormaDto) {
        Integer result = this.proFormaService.create(ProFormaDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editProForma(@RequestBody ProFormaDto ProFormaDto) {
        Integer result = this.proFormaService.edit(ProFormaDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProForma(@PathVariable Long id) {
        this.proFormaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}