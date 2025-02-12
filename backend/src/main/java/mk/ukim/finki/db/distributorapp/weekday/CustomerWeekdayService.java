package mk.ukim.finki.db.distributorapp.weekday;

import mk.ukim.finki.db.distributorapp.weekday.dto.CustomerWeekdayDto;

import java.util.List;

public interface CustomerWeekdayService {
    List<CustomerWeekdayDto> getAllWeekdays();

    List<CustomerWeekdayDto> getWeekdaysByCustomerId(Long customerId);

    Integer create(CustomerWeekdayDto customerWeekdayDto);

    Integer edit(CustomerWeekdayDto customerWeekdayDto);

    void deleteById(Long customerWeekdayId);
}
