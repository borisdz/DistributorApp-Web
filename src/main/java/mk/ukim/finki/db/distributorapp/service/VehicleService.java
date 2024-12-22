package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Driver;
import mk.ukim.finki.db.distributorapp.model.Vehicle;
import mk.ukim.finki.db.distributorapp.model.Warehouse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehiclesByWarehouse(Warehouse warehouse);

    Optional<Vehicle> createVehicle(Integer vehicleCarryWeight, Short vehicleServiceInterval, Integer vehicleKilometers,
                                    LocalDate vehicleLastService, Integer vehicleLastServiceKm, String vehiclePlate,
                                    String vehicleVIN, LocalDate vehicleRegDate, Warehouse warehouse, Driver driver);

    Optional<Vehicle> updateVehicle(Integer id, Integer vehicleCarryWeight, Short vehicleServiceInterval, Integer vehicleKilometers,
                                    LocalDate vehicleLastService, Integer vehicleLastServiceKm, String vehiclePlate,
                                    String vehicleVIN, LocalDate vehicleRegDate, Warehouse warehouse, Driver driver);

    void deleteVehicleById(Integer id);
}
