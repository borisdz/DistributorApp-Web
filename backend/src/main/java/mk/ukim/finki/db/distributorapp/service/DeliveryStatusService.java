package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryStatusDto;

public interface DeliveryStatusService {

    Integer create(DeliveryStatusDto deliveryStatusDto);

    Integer edit(DeliveryStatusDto deliveryStatusDto);

    void deleteById(Short id);
}
