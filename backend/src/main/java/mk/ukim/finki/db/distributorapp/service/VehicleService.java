package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.model.dto.VehicleDto;

import java.util.List;

public interface VehicleService {

    List<VehicleDto> getAllVehicles();

    List<VehicleBasicDto> getVehiclesByWarehouse(Integer warehouseId);

    Integer create(VehicleDto vehicleDto);

    Integer edit(VehicleDto vehicleDto);

    void deleteById(Integer id);

    List<VehicleDto> getVehiclesByManager(Long managerId);
}
