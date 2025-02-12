package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.weekday.dto.CustomerWeekdayDto;
import mk.ukim.finki.db.distributorapp.weekday.CustomerWeekdayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/customerWeekday")
public class RestCustomerWeekdayController {
    private final CustomerWeekdayService customerWeekdayService;

    public RestCustomerWeekdayController(CustomerWeekdayService customerWeekdayService) {
        this.customerWeekdayService = customerWeekdayService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerWeekdayDto>> getAllWeekdays(){
        List<CustomerWeekdayDto> weekdays = customerWeekdayService.getAllWeekdays();
        return ResponseEntity.ok(weekdays);
    }


}
