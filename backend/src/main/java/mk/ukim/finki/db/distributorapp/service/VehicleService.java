package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.model.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;

import java.util.List;

public interface VehicleService {

    List<VehicleDto> getAllVehicles();

    List<VehicleBasicDto> getVehiclesByWarehouse(Integer warehouseId);

    Integer create(VehicleDto vehicleDto);

    Integer edit(VehicleDto vehicleDto);

    void deleteById(Integer id);

    List<VehicleDto> getVehiclesByManager(Manager manager);
}
