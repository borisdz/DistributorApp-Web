package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Driver;
import mk.ukim.finki.db.distributorapp.model.Vehicle;
import mk.ukim.finki.db.distributorapp.repository.DriverRepository;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return this.driverRepository.listAll();
    }

    @Override
    public List<Driver> findAllByName(String name) {
        return this.driverRepository.findAllByName(name);
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return this.driverRepository.findById(id);
    }

    @Override
    public Optional<Driver> create(Long id, Vehicle vehicle) {
        return this.driverRepository.create(id, vehicle.getVehicleId());
    }

    @Override
    public Optional<Driver> edit(Long id, Vehicle vehicle) {
        return this.driverRepository.edit(id, vehicle.getVehicleId());
    }

    @Override
    public void delete(Long id) {
        this.driverRepository.deleteById(id);
    }
}
