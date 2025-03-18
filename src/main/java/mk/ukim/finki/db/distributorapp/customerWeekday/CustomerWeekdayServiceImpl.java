package mk.ukim.finki.db.distributorapp.customerWeekday;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customerWeekday.dto.CustomerWeekdayDto;
import org.springframework.stereotype.Service;

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
