package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CityDto;
import mk.ukim.finki.db.distributorapp.model.dto.CityDtoRegister;

import java.util.List;

public interface CityService {

    List<CityDtoRegister> findAllCityDtos();

    Integer create(CityDto cityDto);

    Integer edit(CityDto cityDto);

    void deleteById(Integer id);
}
