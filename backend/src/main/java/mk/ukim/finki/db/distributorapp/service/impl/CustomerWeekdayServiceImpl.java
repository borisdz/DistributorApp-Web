package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.CustomerWeekdayDto;
import mk.ukim.finki.db.distributorapp.model.entities.CustomerWeekday;
import mk.ukim.finki.db.distributorapp.repository.CustomerWeekdayRepository;
import mk.ukim.finki.db.distributorapp.service.CustomerWeekdayService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerWeekdayServiceImpl implements CustomerWeekdayService {
    private final CustomerWeekdayRepository customerWeekdayRepository;

    public CustomerWeekdayServiceImpl(CustomerWeekdayRepository customerWeekdayRepository) {
        this.customerWeekdayRepository = customerWeekdayRepository;
    }

    private List<CustomerWeekdayDto> buildDto(List<CustomerWeekday> customerWeekdays) {
        List<CustomerWeekdayDto> dtos = new ArrayList<>();
        for (CustomerWeekday customerWeekday : customerWeekdays) {
            CustomerWeekdayDto dto = new CustomerWeekdayDto(
                    customerWeekday.getCustomerDayId(),
                    customerWeekday.getCustomer().getUserId(),
                    customerWeekday.getCustomer().getCustomerCompanyName(),
                    customerWeekday.getDay().getId(),
                    customerWeekday.getDay().getDayName(),
                    customerWeekday.getCustomerDayStartTime(),
                    customerWeekday.getCustomerDayEndTime()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<CustomerWeekdayDto> getAllWeekdays() {
        List<CustomerWeekday> customerWeekdays = customerWeekdayRepository.listAll();
        return buildDto(customerWeekdays);
    }

    @Override
    public List<CustomerWeekdayDto> getWeekdaysByCustomerId(Long customerId) {
        List<CustomerWeekday> customerWeekdays = this.customerWeekdayRepository.findByCustomerId(customerId);
        return buildDto(customerWeekdays);
    }

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
