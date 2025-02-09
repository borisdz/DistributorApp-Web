package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Delivery;
import mk.ukim.finki.db.distributorapp.model.Driver;
import mk.ukim.finki.db.distributorapp.model.Vehicle;
import mk.ukim.finki.db.distributorapp.model.DeliveryStatus;
import mk.ukim.finki.db.distributorapp.repository.DeliveryRepository;
import mk.ukim.finki.db.distributorapp.service.DeliveryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return this.deliveryRepository.listAll();
    }

    @Override
    public List<Delivery> getAllDeliveriesByVehicleId(Vehicle vehicle) {
        return this.deliveryRepository.findAllByVehicle(vehicle.getVehicleId());
    }

    @Override
    public List<Delivery> getAllDeliveriesByDriver(Driver driver) {
        return this.deliveryRepository.findDeliveriesByDriver(driver.getUserId());
    }

    @Override
    public Optional<Delivery> findDeliveryById(Long id) {
        return this.deliveryRepository.findById(id);
    }

    @Override
    public Optional<Delivery> create(LocalDate del_date_created, LocalDate del_date, Integer del_start_km, Integer del_end_km, LocalTime del_start_time, LocalTime del_end_time, DeliveryStatus del_status, Vehicle vehicle) {
        return this.deliveryRepository.create(
                del_date_created,
                del_date,
                del_start_km,
                del_end_km,
                del_start_time,
                del_end_time,
                del_status.getDeliveryStatusId(),
                vehicle.getVehicleId()
        );
    }

    @Override
    public Optional<Delivery> edit(Long del_id, LocalDate del_date_created, LocalDate del_date, Integer del_start_km, Integer del_end_km, LocalTime del_start_time, LocalTime del_end_time, DeliveryStatus del_status, Vehicle vehicle) {
        return this.deliveryRepository.edit(
                del_id,
                del_date_created,
                del_date,
                del_start_km,
                del_end_km,
                del_start_time,
                del_end_time,
                del_status.getDeliveryStatusId(),
                vehicle.getVehicleId()
        );
    }

    @Override
    public void delete(Long del_id) {
        this.deliveryRepository.delete(del_id);
    }
}
