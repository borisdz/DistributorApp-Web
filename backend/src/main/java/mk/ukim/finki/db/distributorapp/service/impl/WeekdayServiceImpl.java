package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.WeekdayDto;
import mk.ukim.finki.db.distributorapp.repository.WeekdayRepository;
import mk.ukim.finki.db.distributorapp.service.WeekdayService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeekdayServiceImpl implements WeekdayService {
    private final WeekdayRepository weekdayRepository;

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
