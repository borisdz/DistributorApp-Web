package mk.ukim.finki.db.distributorapp.delivery;

import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.driver.Driver;
import mk.ukim.finki.db.distributorapp.vehicle.Vehicle;

import java.util.List;

public interface DeliveryService {
    List<DeliveryDto> getAllDeliveries();

    List<DeliveryDto> getAllDeliveriesByVehicleId(Vehicle vehicle);

    List<DeliveryDto> getAllDeliveriesByDriver(Driver driver);

    DeliveryDto findDeliveryById(Long id);

    Integer create(DeliveryDto deliveryDto);

    Integer edit(DeliveryDto deliveryDto);

    void deleteById(Long del_id);

    List<DeliveryDto> getCurrentDeliveriesByCustomer (Long customerId);

    List<DeliveryDto> getCurrentDeliveriesByManager (Long managerId);
}
