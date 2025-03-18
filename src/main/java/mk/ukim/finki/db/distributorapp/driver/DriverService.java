package mk.ukim.finki.db.distributorapp.driver;

import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryEndDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryStartDto;
import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;

import java.util.List;

public interface DriverService {

    DriverDto findById(Long id);

    Integer create(DriverDto driverDto);

    Integer edit(DriverDto driverDto);

    void deleteById(Long id);

    List<DeliverySimpleDto> getNewAssignedDeliveries(Long driverId);

    List<DeliverySimpleDto> getFinishedAssignedDeliveries(Long driverId);

    void startDelivery(DeliveryStartDto delivery);

    List<DeliverySimpleDto> getOngoingDeliveries(Long driverId);

    void endDelivery(DeliveryEndDto delivery);
}
