package mk.ukim.finki.db.distributorapp.vehicle;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleWithDriverDto;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<VehicleBasicDto> getBasicVehiclesByManagerId(Long managerId) {
        return this.vehicleRepository.getBasicVehiclesByManager(managerId);
    }

    @Override
    public List<VehicleWithDriverDto> getAvailableVehiclesForDateByManager(Long id, LocalDate date) {
        List<Map<String, Object>> results = this.vehicleRepository.getAvailableVehiclesForDateByManager(id, date);

        return results.stream()
                .map(this::mapToVehicleWithDriverDto)
                .collect(Collectors.toList());
    }

    private VehicleWithDriverDto mapToVehicleWithDriverDto(Map<String, Object> row) {
        VehicleWithDriverDto dto = new VehicleWithDriverDto();

        dto.setId(getInteger(row, "id"));
        dto.setCarryWeight(getInteger(row, "carryWeight"));
        dto.setKilometers(getInteger(row, "kilometers"));
        dto.setServiceInterval(getShort(row, "serviceInterval"));

        Date sqlDate = (java.sql.Date) row.get("lastServiceDate");
        if (sqlDate != null) {
            dto.setLastServiceDate(new java.util.Date(sqlDate.getTime()));
        }
        dto.setLastServiceKm(getInteger(row, "lastServiceKm"));
        dto.setVin(getString(row, "vin"));
        dto.setPlate(getString(row, "plate"));

        Date sqlRegDate = (java.sql.Date) row.get("registrationDate");
        if(sqlRegDate != null) {
            dto.setRegistrationDate(new java.util.Date(sqlRegDate.getTime()));
        }

        Long driverId = getLong(row, "driverId");
        if(driverId!=null){
            DriverDto driverDto = new DriverDto();
            driverDto.setId(driverId);
            driverDto.setName(getString(row, "name"));
            driverDto.setEmail(getString(row, "email"));
            driverDto.setPhone(getString(row, "phone"));
            driverDto.setImage(getString(row, "img"));
            driverDto.setVehId(getInteger(row, "id"));
            dto.setDriver(driverDto);
        }

        return dto;
    }

    private Long getLong(Map<String, Object> row, String key) {
        Object value = row.get(key);
        return (value != null) ? ((Number) value).longValue() : null;
    }

    private Double getDouble(Map<String, Object> row, String key) {
        Object value = row.get(key);
        return (value != null) ? ((Number) value).doubleValue() : null;
    }

    private Integer getInteger(Map<String, Object> row, String key) {
        Object value = row.get(key);
        return (value != null) ? ((Number) value).intValue() : null;
    }

    private String getString(Map<String, Object> row, String key) {
        Object value = row.get(key);
        return (value != null) ? value.toString() : null;
    }

    private Short getShort(Map<String, Object> row, String key) {
        Object value = row.get(key);
        return (value != null) ? ((Number) value).shortValue() : null;
    }
}
