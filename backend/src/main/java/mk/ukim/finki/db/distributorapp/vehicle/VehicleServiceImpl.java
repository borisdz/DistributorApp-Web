package mk.ukim.finki.db.distributorapp.vehicle;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

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
    public List<VehicleDto> getVehiclesByManager(Long managerId) {
        return this.vehicleRepository.getVehiclesByManager(managerId);
    }
}
