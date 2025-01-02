package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<Driver> getAllDrivers();

    List<Driver> findAllByName(String name);

    Optional<Driver> findById(Long id);

    Optional<Driver> create(Long id, Vehicle vehicle);

    Optional<Driver> edit(Long id, Vehicle vehicle);

    void delete(Long id);
}
