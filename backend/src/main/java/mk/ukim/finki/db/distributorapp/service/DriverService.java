package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryEndDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryStartDto;
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

    List<DeliverySimpleDto> getNewAssignedDeliveries(Long driverId);

    List<DeliverySimpleDto> getFinishedAssignedDeliveries(Long driverId);

    void startDelivery(DeliveryStartDto delivery);

    List<DeliverySimpleDto> getOngoingDeliveries(Long driverId);

    void endDelivery(DeliveryEndDto delivery);
}
