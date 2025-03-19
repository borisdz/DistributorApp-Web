package mk.ukim.finki.db.distributorapp.customerWeekday;

import mk.ukim.finki.db.distributorapp.customerWeekday.dto.CustomerWeekdayDto;

public interface CustomerWeekdayService {

    Integer create(CustomerWeekdayDto customerWeekdayDto);

    Integer edit(CustomerWeekdayDto customerWeekdayDto);

    void deleteById(Long customerWeekdayId);
}
