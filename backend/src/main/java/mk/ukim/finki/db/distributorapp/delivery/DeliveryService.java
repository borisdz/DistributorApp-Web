package mk.ukim.finki.db.distributorapp.delivery;

import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;

import java.util.List;

public interface DeliveryService {

    DeliveryFullDto findDeliveryById(Long id);

    Integer create(DeliveryCreateDto deliveryDto);

    Integer edit(DeliveryFullDto deliveryDto);

    void deleteById(Long del_id);

    List<DeliveryDto> getCurrentDeliveriesByCustomer(Long customerId);

    List<DeliveryDto> getCurrentDeliveriesByManager(Long managerId);

    List<DeliverySimpleDto> getDeliveriesByVehicle(Integer vehicleId);
}
