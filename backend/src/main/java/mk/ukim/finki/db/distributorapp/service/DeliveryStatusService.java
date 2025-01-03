package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryStatusDto;

import java.util.List;

public interface DeliveryStatusService {
    List<DeliveryStatusDto> listDeliveryStatus();

    DeliveryStatusDto getDeliveryStatusById(Short id);

    Integer create(DeliveryStatusDto deliveryStatusDto);

    Integer edit(DeliveryStatusDto deliveryStatusDto);

    List<DeliveryStatusDto> getDeliveryStatusByName(String name);

    void delete(Short id);
}
