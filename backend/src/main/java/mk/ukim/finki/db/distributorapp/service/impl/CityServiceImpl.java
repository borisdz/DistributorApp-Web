package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.CityDto;
import mk.ukim.finki.db.distributorapp.model.entities.City;
import mk.ukim.finki.db.distributorapp.repository.CityRepository;
import mk.ukim.finki.db.distributorapp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    private List<CityDto> buildDto(List<City> cities) {
        List<CityDto> dtos = new ArrayList<>();
        for (City city : cities) {
            CityDto dto = new CityDto(
                    city.getCityId(),
                    city.getCityName(),
                    city.getRegion().getRegionId(),
                    city.getRegion().getRegionName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<CityDto> listCities() {
        List<City> cities = cityRepository.listAll();
        return buildDto(cities);
    }

    @Override
    public CityDto getCityById(Long id) {
        City city = cityRepository.findById(id).orElse(null);

        assert city != null;
        return new CityDto(
                city.getCityId(),
                city.getCityName(),
                city.getRegion().getRegionId(),
                city.getRegion().getRegionName()
        );
    }

    @Override
    public Optional<City> getCityObjById(Long id){
        return this.cityRepository.findById(id);
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
    public void deleteById(Long id) {
        this.cityRepository.deleteById(id);
    }

    @Override
    public List<CityDto> searchCities(String text) {
        List<City> cities = this.cityRepository.findByName("'" + text + "'");
        return buildDto(cities);
    }
}
