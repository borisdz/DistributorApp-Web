package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.manager.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.manager.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/manager")
public class RestManagerController {
    private final ManagerService managerService;

    public RestManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        List<ManagerDto> managers = managerService.getAllManagers();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/add")
    public String addManager() {
        return "create-manager";
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
