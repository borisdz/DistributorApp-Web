package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;

import java.util.List;

public interface DriverService {

    List<DriverDto> getAllDrivers();

    List<DriverDto> findAllByName(String name);

    DriverDto findById(Long id);

    Driver getDriverObjById(Long id);

    Integer create(DriverDto driverDto);

    Integer edit(DriverDto driverDto);

    void deleteById(Long id);

    List<DeliveryDto> getNewAssignedDeliveries(Long driverId);

    List<DeliveryDto> getFinishedAssignedDeliveries(Long driverId);
}
