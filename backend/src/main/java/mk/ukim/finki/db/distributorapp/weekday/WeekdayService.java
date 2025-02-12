package mk.ukim.finki.db.distributorapp.weekday;

import mk.ukim.finki.db.distributorapp.weekday.dto.WeekdayDto;

import java.util.List;

public interface WeekdayService {
    List<WeekdayDto> getAllWeekdays();

    WeekdayDto getWeekdayByName(String weekdayName);

    WeekdayDto findWeekdayById(Short id);

    Integer create(WeekdayDto weekdayDto);

    Integer edit(WeekdayDto weekdayDto);

    void deleteById(Short id);
}
