package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Driver;
import mk.ukim.finki.db.distributorapp.model.Vehicle;
import mk.ukim.finki.db.distributorapp.model.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.VehicleRepository;
import mk.ukim.finki.db.distributorapp.service.VehicleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.listAll();
    }

    @Override
    public List<Vehicle> getVehiclesByWarehouse(Warehouse warehouse) {
        return this.vehicleRepository.findAllByWarehouse(warehouse.getWarehouse_id());
    }

    @Override
    public Optional<Vehicle> createVehicle(Integer vehicleCarryWeight, Short vehicleServiceInterval, Integer vehicleKilometers,
                                           LocalDate vehicleLastService, Integer vehicleLastServiceKm, String vehiclePlate,
                                           String vehicleVIN, LocalDate vehicleRegDate, Warehouse warehouse, Driver driver) {

        return this.vehicleRepository.create(vehicleCarryWeight, vehicleServiceInterval, vehicleKilometers, vehicleLastService,
                vehicleLastServiceKm, vehiclePlate, vehicleVIN, vehicleRegDate, warehouse.getWarehouse_id(), driver.getUser_id());
    }

    @Override
    public Optional<Vehicle> updateVehicle(Integer id, Integer vehicleCarryWeight, Short vehicleServiceInterval, Integer vehicleKilometers,
                                           LocalDate vehicleLastService, Integer vehicleLastServiceKm, String vehiclePlate, String vehicleVIN,
                                           LocalDate vehicleRegDate, Warehouse warehouse, Driver driver) {


        return this.vehicleRepository.edit(id, vehicleCarryWeight, vehicleServiceInterval, vehicleKilometers, vehicleLastService, vehicleLastServiceKm,
                vehiclePlate, vehicleVIN, vehicleRegDate, warehouse.getWarehouse_id(), driver.getUser_id());
    }

    @Override
    public void deleteVehicleById(Integer id) {
        this.vehicleRepository.delete(id);
    }
}
