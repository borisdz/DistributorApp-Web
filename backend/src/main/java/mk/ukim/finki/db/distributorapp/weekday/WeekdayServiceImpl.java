package mk.ukim.finki.db.distributorapp.weekday;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.weekday.dto.WeekdayDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekdayServiceImpl implements WeekdayService {
    private final WeekdayRepository weekdayRepository;

    private List<WeekdayDto> buildDto(List<Weekday> weekdays) {
        List<WeekdayDto> dtos = new ArrayList<>();
        for (Weekday weekday : weekdays) {
            WeekdayDto dto = new WeekdayDto(
                    weekday.getId(),
                    weekday.getDayName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<WeekdayDto> getAllWeekdays() {
        List<Weekday> weekdays = this.weekdayRepository.listAll();
        return buildDto(weekdays);
    }

    @Override
    public WeekdayDto getWeekdayByName(String weekdayName) {
        Weekday weekday = this.weekdayRepository.findWeekdayByName("'"+weekdayName+"'").get();
        return new WeekdayDto(
                weekday.getId(),
                weekday.getDayName()
        );
    }

    @Override
    public WeekdayDto findWeekdayById(Short id) {
        Weekday weekday = this.weekdayRepository.findWeekdayById(id).get();
        return new WeekdayDto(
                weekday.getId(),
                weekday.getDayName()
        );
    }

    @Override
    public Integer create(WeekdayDto weekdayDto) {
        return this.weekdayRepository.create(
                weekdayDto.getDayName()
        );
    }

    @Override
    public Integer edit(WeekdayDto weekdayDto) {
        return this.weekdayRepository.edit(
                weekdayDto.getId(),
                weekdayDto.getDayName()
        );
    }

    @Override
    public void deleteById(Short id) {
        this.weekdayRepository.deleteById(id);
    }
}
