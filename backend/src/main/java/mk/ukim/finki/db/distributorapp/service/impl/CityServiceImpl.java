package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.CityDto;
import mk.ukim.finki.db.distributorapp.model.dto.CityDtoRegister;
import mk.ukim.finki.db.distributorapp.repository.CityRepository;
import mk.ukim.finki.db.distributorapp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public List<CityDtoRegister> findAllCityDtos() {
        return this.cityRepository.findAllCityDtos();
    }

    @Override
    public Integer create(CityDto dto) {
        return this.cityRepository.create(dto.getName(), dto.getRegionId());
    }

    @Override
    public Integer edit(CityDto dto) {
        return this.cityRepository.edit(dto.getId(), dto.getName(), dto.getRegionId());
    }

    @Override
    public void deleteById(Integer id) {
        this.cityRepository.deleteById(id);
    }
}
