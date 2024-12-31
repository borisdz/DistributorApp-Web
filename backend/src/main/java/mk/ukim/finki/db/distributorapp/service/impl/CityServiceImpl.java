package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.City;
import mk.ukim.finki.db.distributorapp.model.Region;
import mk.ukim.finki.db.distributorapp.repository.CityRepository;
import mk.ukim.finki.db.distributorapp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> listCities() {
        return this.cityRepository.listAll();
    }

    @Override
    public Optional<City> getCityById(Long id) {
        return this.cityRepository.findById(id);
    }

    @Override
    public Optional<City> create(String name, Region region) {
        return this.cityRepository.create(name, region.getRegionId());
    }

    @Override
    public Optional<City> edit(Long id, String name, Region region) {
        return this.cityRepository.edit(id, name, region.getRegionId());
    }

    @Override
    public void delete(Long id) {
        this.cityRepository.deleteById(id);
    }

    @Override
    public List<City> searchCities(String text) {
        return this.cityRepository.findByName(text);
    }
}
