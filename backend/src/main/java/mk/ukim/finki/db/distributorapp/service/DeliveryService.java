package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;

import java.text.ParseException;
import java.util.List;

public interface DeliveryService {
    List<DeliveryDto> getAllDeliveries();

    List<DeliveryDto> getAllDeliveriesByVehicleId(Vehicle vehicle);

    List<DeliveryDto> getAllDeliveriesByDriver(Driver driver);

    DeliveryDto findDeliveryById(Long id);

    Integer create(DeliveryCreateDto deliveryDto) throws ParseException;

    Integer edit(DeliveryDto deliveryDto);

    void deleteById(Long del_id);

    List<DeliveryDto> getCurrentDeliveriesByCustomer (Long customerId);

    List<DeliveryDto> getCurrentDeliveriesByManager (Long managerId);
}
