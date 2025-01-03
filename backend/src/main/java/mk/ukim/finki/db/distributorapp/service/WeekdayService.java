package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.WeekdayDto;

import java.util.List;

public interface WeekdayService {
    List<WeekdayDto> getAllWeekdays();

    WeekdayDto getWeekdayByName(String weekdayName);

    WeekdayDto findWeekdayById(Short id);

    Integer create(WeekdayDto weekdayDto);

    Integer edit(WeekdayDto weekdayDto);

    void delete(Short id);
}
