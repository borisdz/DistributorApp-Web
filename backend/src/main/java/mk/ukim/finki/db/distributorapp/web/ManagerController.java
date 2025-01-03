package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        List<ManagerDto> managers = managerService.getAllManagers();
        return ResponseEntity.ok(managers);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addManager(@RequestBody ManagerDto ManagerDto) {
        Integer result = this.managerService.create(ManagerDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editManager(@RequestBody ManagerDto ManagerDto) {
        Integer result = this.managerService.edit(ManagerDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        this.managerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
