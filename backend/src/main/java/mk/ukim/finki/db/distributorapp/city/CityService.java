package mk.ukim.finki.db.distributorapp.city;

import mk.ukim.finki.db.distributorapp.city.dto.CityDto;
import mk.ukim.finki.db.distributorapp.city.dto.CityDtoRegister;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> listCitiesObj();

    List<CityDto> listCities();

    List<CityDtoRegister> findAllCityDtos();

    CityDto getCityById(Integer id);

    Optional<City> getCityObjById(Integer id);

    Integer create(CityDto cityDto);

    Integer edit(CityDto cityDto);

    void deleteById(Integer id);

    List<CityDto> searchCities(String text);
}
