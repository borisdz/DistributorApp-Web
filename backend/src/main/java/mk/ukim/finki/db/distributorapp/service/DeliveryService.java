package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;

import java.util.List;

public interface DeliveryService {
    List<DeliveryDto> getAllDeliveries();

    List<DeliveryDto> getAllDeliveriesByVehicleId(Vehicle vehicle);

    List<DeliveryDto> getAllDeliveriesByDriver(Driver driver);

    DeliveryDto findDeliveryById(Long id);

    Integer create(DeliveryDto deliveryDto);

    Integer edit(DeliveryDto deliveryDto);

    void deleteById(Long del_id);

    List<DeliveryDto> getAllNewDeliveriesByDriver(Driver driver);

    List<DeliveryDto> getCurrentDeliveriesByCustomer (Customer customer);

    List<DeliveryDto> getCurrentDeliveriesByManager (Long managerId);
}
