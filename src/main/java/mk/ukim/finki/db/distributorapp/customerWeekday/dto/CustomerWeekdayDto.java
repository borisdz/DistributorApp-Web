package mk.ukim.finki.db.distributorapp.customerWeekday.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CustomerWeekdayDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private Short dayId;
    private String dayName;
    private LocalTime dayStartTime;
    private LocalTime dayEndTime;
}
