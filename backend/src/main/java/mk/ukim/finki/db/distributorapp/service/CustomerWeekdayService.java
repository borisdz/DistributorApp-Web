package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CustomerWeekdayDto;

import java.util.List;

public interface CustomerWeekdayService {
    List<CustomerWeekdayDto> getAllWeekdays();

    List<CustomerWeekdayDto> getWeekdaysByCustomerId(Long customerId);

    Integer create(CustomerWeekdayDto customerWeekdayDto);

    Integer edit(CustomerWeekdayDto customerWeekdayDto);

    void delete(Long customerWeekdayId);
}
