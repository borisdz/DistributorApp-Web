package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> listCities();

    CityDto getCityById(Long id);

    Integer create(CityDto cityDto);

    Integer edit(CityDto cityDto);

    void delete(Long id);

    List<CityDto> searchCities(String text);
}
