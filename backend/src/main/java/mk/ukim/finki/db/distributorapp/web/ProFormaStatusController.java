package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaStatusDto;
import mk.ukim.finki.db.distributorapp.service.ProFormaStatusService;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ProFormaStatusDto> addProFormaStatus(@RequestBody ProFormaStatusDto ProFormaStatusDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ProFormaStatusDto> editProFormaStatus(@RequestBody ProFormaStatusDto ProFormaStatusDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProFormaStatusDto> deleteProFormaStatus(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
