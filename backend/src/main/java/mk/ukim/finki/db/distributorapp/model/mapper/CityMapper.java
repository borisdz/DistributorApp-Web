package mk.ukim.finki.db.distributorapp.model.mapper;

import mk.ukim.finki.db.distributorapp.model.dto.CityDto;
import mk.ukim.finki.db.distributorapp.model.entities.City;

public class CityMapper {
    public static CityDto toCityDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getCityId());
        cityDto.setName(city.getCityName());
        return cityDto;
    }
}
