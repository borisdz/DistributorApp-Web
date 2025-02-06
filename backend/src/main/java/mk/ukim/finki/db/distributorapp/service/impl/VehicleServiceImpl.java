package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.model.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;
import mk.ukim.finki.db.distributorapp.repository.VehicleRepository;
import mk.ukim.finki.db.distributorapp.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private VehicleDto buildDto(Vehicle veh) {
        return new VehicleDto(
                veh.getVehicleId(),
                veh.getVehicleCarryWeight(),
                veh.getVehicleServiceInterval(),
                veh.getVehicleKilometers(),
                veh.getVehicleLastService(),
                veh.getVehicleLastServiceKm(),
                veh.getVehiclePlate(),
                veh.getVehicleVin(),
                veh.getVehicleRegDate(),
                veh.getWarehouse().getWarehouseId(),
                veh.getWarehouse().getCity().getCityName(),
                veh.getWarehouse().getCity().getRegion().getRegionName(),
                veh.getDriver().getUserId(),
                veh.getDriver().getUsername(),
                veh.getDriver().getUserEmail(),
                veh.getDriver().getUserMobile(),
                veh.getDriver().getUserImage()
        );
    }

    private List<VehicleDto> buildDtoList(List<Vehicle> vehicles) {
        List<VehicleDto> dtos = new ArrayList<>();
        for (Vehicle veh : vehicles) {
            VehicleDto dto = buildDto(veh);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<Vehicle> vehicles = this.vehicleRepository.listAll();
        return buildDtoList(vehicles);
    }

    @Override
    public List<VehicleBasicDto> getVehiclesByWarehouse(Integer warehouseId) {
        return this.vehicleRepository.findAllByWarehouseDto(warehouseId);
    }

    @Override
    public Integer create(VehicleDto vehicleDto) {
        return this.vehicleRepository.create(
                vehicleDto.getCarryWeight(),
                vehicleDto.getServiceInterval(),
                vehicleDto.getKilometers(),
                vehicleDto.getLastServiceDate(),
                vehicleDto.getLastServiceKm(),
                vehicleDto.getPlate(),
                vehicleDto.getVin(),
                vehicleDto.getRegistrationDate(),
                vehicleDto.getWhId());
    }

    @Override
    public Integer edit(VehicleDto vehicleDto) {

        return this.vehicleRepository.edit(
                vehicleDto.getId(),
                vehicleDto.getCarryWeight(),
                vehicleDto.getServiceInterval(),
                vehicleDto.getKilometers(),
                vehicleDto.getLastServiceDate(),
                vehicleDto.getLastServiceKm(),
                vehicleDto.getPlate(),
                vehicleDto.getVin(),
                vehicleDto.getRegistrationDate(),
                vehicleDto.getWhId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        this.vehicleRepository.delete(id);
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<VehicleDto> getVehiclesByManager(Manager manager) {
        List<Vehicle> vehicles = this.vehicleRepository.getVehiclesByManager(manager.getUserId());
        return buildDtoList(vehicles);
    }
}
