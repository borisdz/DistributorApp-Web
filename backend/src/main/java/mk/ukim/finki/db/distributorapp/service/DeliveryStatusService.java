package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.DeliveryStatus;

import java.util.List;
import java.util.Optional;

public interface DeliveryStatusService {
    List<DeliveryStatus> listDeliveryStatus();

    Optional<DeliveryStatus> getDeliveryStatusById(Short id);

    Optional<DeliveryStatus> createDeliveryStatus(String name, String description);

    Optional<DeliveryStatus> updateDeliveryStatus(Short id, String name, String description);

    List<DeliveryStatus> getDeliveryStatusByName(String name);

    void delete(Short id);
}
