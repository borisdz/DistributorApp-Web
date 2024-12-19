package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.City;
import mk.ukim.finki.db.distributorapp.repository.CityRepository;
import mk.ukim.finki.db.distributorapp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityService cityService, CityRepository cityRepository) {
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
    public Optional<City> createCity(String name) {
        return this.cityRepository.create(name);
    }

    @Override
    public Optional<City> updateCity(Long id, String name) {
        return this.cityRepository.edit(id, name);
    }

    @Override
    public void delete(Long id) {
        this.cityRepository.deleteById(id);
    }

    @Override
    public Optional<City> searchCities(String text) {
        return Optional.empty();
    }
}
