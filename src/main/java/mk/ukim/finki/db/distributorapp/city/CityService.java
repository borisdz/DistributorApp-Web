package mk.ukim.finki.db.distributorapp.city;

import mk.ukim.finki.db.distributorapp.city.dto.CityDto;
import mk.ukim.finki.db.distributorapp.city.dto.CityDtoRegister;

import java.util.List;

public interface CityService {

    List<CityDtoRegister> findAllCityDtos();

    Integer create(CityDto cityDto);

    Integer edit(CityDto cityDto);

    void deleteById(Integer id);
}
