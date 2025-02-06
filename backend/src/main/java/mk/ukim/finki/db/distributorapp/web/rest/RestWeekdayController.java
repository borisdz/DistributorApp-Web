package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.dto.WeekdayDto;
import mk.ukim.finki.db.distributorapp.service.WeekdayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/weekday")
public class RestWeekdayController {
    private final WeekdayService weekdayService;

    public RestWeekdayController(WeekdayService weekdayService) {
        this.weekdayService = weekdayService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WeekdayDto>> getAllWeekdays(){
        List<WeekdayDto> weekdays = weekdayService.getAllWeekdays();
        return ResponseEntity.ok(weekdays);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addWeekday(@RequestBody WeekdayDto weekdayDto){
        Integer result = this.weekdayService.create(weekdayDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editWeekday(@RequestBody WeekdayDto weekdayDto){
        Integer result = this.weekdayService.edit(weekdayDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWeekday(@PathVariable Short id){
        this.weekdayService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
