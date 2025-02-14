package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.WeekdayDto;

import java.util.List;

public interface WeekdayService {

    Integer create(WeekdayDto weekdayDto);

    Integer edit(WeekdayDto weekdayDto);

    void deleteById(Short id);
}
