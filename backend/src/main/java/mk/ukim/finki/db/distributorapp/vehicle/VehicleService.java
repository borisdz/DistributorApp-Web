package mk.ukim.finki.db.distributorapp.vehicle;

import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleDto;

import java.util.List;

public interface VehicleService {

    List<VehicleDto> getAllVehicles();

    List<VehicleBasicDto> getVehiclesByWarehouse(Integer warehouseId);

    Integer create(VehicleDto vehicleDto);

    Integer edit(VehicleDto vehicleDto);

    void deleteById(Integer id);

    List<VehicleDto> getVehiclesByManager(Long managerId);
}
