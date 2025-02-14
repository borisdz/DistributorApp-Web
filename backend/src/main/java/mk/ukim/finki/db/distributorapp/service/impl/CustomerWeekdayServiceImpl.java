package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.CustomerWeekdayDto;
import mk.ukim.finki.db.distributorapp.model.entities.CustomerWeekday;
import mk.ukim.finki.db.distributorapp.repository.CustomerWeekdayRepository;
import mk.ukim.finki.db.distributorapp.service.CustomerWeekdayService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerWeekdayServiceImpl implements CustomerWeekdayService {
    private final CustomerWeekdayRepository customerWeekdayRepository;

    @Override
    public Integer create(CustomerWeekdayDto customerWeekdayDto) {
        return this.customerWeekdayRepository.create(
                customerWeekdayDto.getCustomerId(),
                customerWeekdayDto.getDayId(),
                customerWeekdayDto.getDayStartTime(),
                customerWeekdayDto.getDayEndTime()
        );
    }

    @Override
    public Integer edit(CustomerWeekdayDto customerWeekdayDto) {
        return this.customerWeekdayRepository.edit(
                customerWeekdayDto.getCustomerId(),
                customerWeekdayDto.getCustomerId(),
                customerWeekdayDto.getDayId(),
                customerWeekdayDto.getDayStartTime(),
                customerWeekdayDto.getDayEndTime()
        );
    }

    @Override
    public void deleteById(Long customerWeekdayId) {
        this.customerWeekdayRepository.deleteById(customerWeekdayId);
    }
}
