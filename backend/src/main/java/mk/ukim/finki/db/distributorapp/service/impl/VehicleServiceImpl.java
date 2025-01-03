package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.VehicleRepository;
import mk.ukim.finki.db.distributorapp.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

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
    public List<VehicleDto> getVehiclesByWarehouse(Warehouse warehouse) {
        List<Vehicle> vehicles = this.vehicleRepository.findAllByWarehouse(warehouse.getWarehouseId());
        return buildDtoList(vehicles);
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
}
