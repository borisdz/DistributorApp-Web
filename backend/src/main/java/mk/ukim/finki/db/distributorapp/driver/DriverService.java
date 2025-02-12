package mk.ukim.finki.db.distributorapp.driver;

import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;

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
