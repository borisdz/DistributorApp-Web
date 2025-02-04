package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CityDto;
import mk.ukim.finki.db.distributorapp.model.dto.CityDtoRegister;
import mk.ukim.finki.db.distributorapp.model.entities.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> listCitiesObj();

    List<CityDto> listCities();

    List<CityDtoRegister> findAllCityDtos();

    CityDto getCityById(Long id);

    Optional<City> getCityObjById(Long id);

    Integer create(CityDto cityDto);

    Integer edit(CityDto cityDto);

    void deleteById(Long id);

    List<CityDto> searchCities(String text);
}
