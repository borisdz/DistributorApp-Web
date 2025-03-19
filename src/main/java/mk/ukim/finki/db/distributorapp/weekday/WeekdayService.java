package mk.ukim.finki.db.distributorapp.weekday;

import mk.ukim.finki.db.distributorapp.weekday.dto.WeekdayDto;

public interface WeekdayService {

    Integer create(WeekdayDto weekdayDto);

    Integer edit(WeekdayDto weekdayDto);

    void deleteById(Short id);
}
