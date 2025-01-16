package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DriverDto;

import java.util.List;

public interface DriverService {

    List<DriverDto> getAllDrivers();

    List<DriverDto> findAllByName(String name);

    DriverDto findById(Long id);

    Integer create(DriverDto driverDto);

    Integer edit(DriverDto driverDto);

    void deleteById(Long id);
}
