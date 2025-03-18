package mk.ukim.finki.db.distributorapp.deliveryStatus;

import mk.ukim.finki.db.distributorapp.deliveryStatus.dto.DeliveryStatusDto;

public interface DeliveryStatusService {

    Integer create(DeliveryStatusDto deliveryStatusDto);

    Integer edit(DeliveryStatusDto deliveryStatusDto);

    void deleteById(Short id);
}
