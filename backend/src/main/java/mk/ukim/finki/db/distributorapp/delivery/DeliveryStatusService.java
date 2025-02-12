package mk.ukim.finki.db.distributorapp.delivery;

import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryStatusDto;

import java.util.List;

public interface DeliveryStatusService {

    List<DeliveryStatusDto> getAllDeliveryStatus();

    DeliveryStatusDto getDeliveryStatusById(Short id);

    Integer create(DeliveryStatusDto deliveryStatusDto);

    Integer edit(DeliveryStatusDto deliveryStatusDto);

    List<DeliveryStatusDto> getDeliveryStatusByName(String name);

    void deleteById(Short id);
}
