package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;

import java.util.List;

public interface VehicleService {

    List<VehicleDto> getAllVehicles();

    List<VehicleDto> getVehiclesByWarehouse(Warehouse warehouse);

    Integer create(VehicleDto vehicleDto);

    Integer edit(VehicleDto vehicleDto);

    void deleteVehicleById(Integer id);
}
