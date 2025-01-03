package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaStatusDto;
import mk.ukim.finki.db.distributorapp.service.ProFormaStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pfStatus")
public class ProFormaStatusController {
    private final ProFormaStatusService proFormaStatusService;

    public ProFormaStatusController(ProFormaStatusService proFormaStatusService) {
        this.proFormaStatusService = proFormaStatusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProFormaStatusDto>> getAllProFormaStatuss() {
        List<ProFormaStatusDto> statuses = proFormaStatusService.getAllProFormaStatus();
        return ResponseEntity.ok(statuses);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addProFormaStatus(@RequestBody ProFormaStatusDto ProFormaStatusDto) {
        Integer result = this.proFormaStatusService.create(ProFormaStatusDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editProFormaStatus(@RequestBody ProFormaStatusDto ProFormaStatusDto) {
        Integer result = this.proFormaStatusService.edit(ProFormaStatusDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProFormaStatus(@PathVariable Short id) {
        this.proFormaStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
