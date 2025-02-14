package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliverySimpleDto;

import java.util.List;

public interface DeliveryService {

    DeliveryFullDto findDeliveryById(Long id);

    Integer create(DeliveryCreateDto deliveryDto);

    Integer edit(DeliveryFullDto deliveryDto);

    void deleteById(Long del_id);

    List<DeliveryDto> getCurrentDeliveriesByCustomer (Long customerId);

    List<DeliveryDto> getCurrentDeliveriesByManager (Long managerId);

    List<DeliverySimpleDto> getDeliveriesByVehicle (Integer vehicleId);
}
