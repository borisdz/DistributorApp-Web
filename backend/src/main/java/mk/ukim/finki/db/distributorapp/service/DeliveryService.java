package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Delivery;
import mk.ukim.finki.db.distributorapp.model.Driver;
import mk.ukim.finki.db.distributorapp.model.Vehicle;
import mk.ukim.finki.db.distributorapp.model.DeliveryStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<Delivery> getAllDeliveries();

    List<Delivery> getAllDeliveriesByVehicleId(Vehicle vehicle);

    List<Delivery> getAllDeliveriesByDriver(Driver driver);

    Optional<Delivery> findDeliveryById(Long id);

    Optional<Delivery> create(
            LocalDate del_date_created,
            LocalDate del_date,
            Integer del_start_km,
            Integer del_end_km,
            LocalTime del_start_time,
            LocalTime del_end_time,
            DeliveryStatus del_status,
            Vehicle vehicle
    );

    Optional<Delivery> edit(
            Long del_id,
            LocalDate del_date_created,
            LocalDate del_date,
            Integer del_start_km,
            Integer del_end_km,
            LocalTime del_start_time,
            LocalTime del_end_time,
            DeliveryStatus del_status,
            Vehicle vehicle
    );

    void delete(Long del_id);
}
