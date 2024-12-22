package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.statuses.Delivery_Status;

import java.util.List;
import java.util.Optional;

public interface DeliveryStatusService {
    List<Delivery_Status> listDeliveryStatus();

    Optional<Delivery_Status> getDeliveryStatusById(Short id);

    Optional<Delivery_Status> createDeliveryStatus(String name, String description);

    Optional<Delivery_Status> updateDeliveryStatus(Short id, String name, String description);

    List<Delivery_Status> getDeliveryStatusByName(String name);

    void delete(Short id);
}
