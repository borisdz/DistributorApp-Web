package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.statuses.Delivery_Status;
import mk.ukim.finki.db.distributorapp.repository.DeliveryStatusRepository;
import mk.ukim.finki.db.distributorapp.service.DeliveryStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryStatusServiceImpl implements DeliveryStatusService {
    private final DeliveryStatusRepository deliveryStatusRepository;

    public DeliveryStatusServiceImpl(DeliveryStatusRepository deliveryStatusRepository) {
        this.deliveryStatusRepository = deliveryStatusRepository;
    }

    @Override
    public List<Delivery_Status> listDeliveryStatus() {
        return this.deliveryStatusRepository.findAll();
    }

    @Override
    public Optional<Delivery_Status> getDeliveryStatusById(Short id) {
        return this.deliveryStatusRepository.findById(id);
    }

    @Override
    public Optional<Delivery_Status> createDeliveryStatus(String name, String description) {
        return this.deliveryStatusRepository.create(name, description);
    }

    @Override
    public Optional<Delivery_Status> updateDeliveryStatus(Short id, String name, String description) {
        return this.deliveryStatusRepository.edit(id, name, description);
    }

    @Override
    public List<Delivery_Status> getDeliveryStatusByName(String name) {
        return this.deliveryStatusRepository.findAllByName(name);
    }

    @Override
    public void delete(Short id) {
        this.deliveryStatusRepository.delete(id);
    }
}
