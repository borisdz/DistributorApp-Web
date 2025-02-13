package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;

import java.util.List;

public interface DeliveryService {
    List<DeliveryDto> getAllDeliveries();

    List<DeliveryDto> getAllDeliveriesByVehicleId(Vehicle vehicle);

    List<DeliveryDto> getAllDeliveriesByDriver(Driver driver);

    DeliveryFullDto findDeliveryById(Long id);

    Integer create(DeliveryCreateDto deliveryDto);

    Integer edit(DeliveryFullDto deliveryDto);

    void deleteById(Long del_id);

    List<DeliveryDto> getCurrentDeliveriesByCustomer (Long customerId);

    List<DeliveryDto> getCurrentDeliveriesByManager (Long managerId);

    List<DeliverySimpleDto> getDeliveriesByVehicle (Integer vehicleId);
}
