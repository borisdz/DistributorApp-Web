package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.City;
import mk.ukim.finki.db.distributorapp.model.Region;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> listCities();

    Optional<City> getCityById(Long id);

    Optional<City> create(String name, Region region);

    Optional<City> edit(Long id, String name, Region region);

    void delete(Long id);

    List<City> searchCities(String text);
}
