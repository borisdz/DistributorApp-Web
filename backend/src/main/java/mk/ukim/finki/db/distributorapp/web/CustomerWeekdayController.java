package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.CustomerWeekdayDto;
import mk.ukim.finki.db.distributorapp.service.CustomerWeekdayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customerWeekday")
public class CustomerWeekdayController {
    private final CustomerWeekdayService customerWeekdayService;

    public CustomerWeekdayController(CustomerWeekdayService customerWeekdayService) {
        this.customerWeekdayService = customerWeekdayService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerWeekdayDto>> getAllWeekdays(){
        List<CustomerWeekdayDto> weekdays = customerWeekdayService.getAllWeekdays();
        return ResponseEntity.ok(weekdays);
    }


}
