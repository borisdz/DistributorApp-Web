package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.repository.DriverRepository;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    private List<DriverDto> buildDto(List<Driver> drivers) {
        List<DriverDto> dtos = new ArrayList<>();
        for (Driver driver : drivers) {
            DriverDto dto = new DriverDto(
                    driver.getUserId(),
                    driver.getUsername(),
                    driver.getUserEmail(),
                    driver.getUserMobile(),
                    driver.getUserImage(),
                    driver.getVehicle().getVehicleId()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<DriverDto> getAllDrivers() {
        List<Driver> drivers = this.driverRepository.listAll();
        return buildDto(drivers);
    }

    @Override
    public List<DriverDto> findAllByName(String name) {
        List<Driver> drivers = this.driverRepository.findAllByName("'"+name+"'");
        return buildDto(drivers);
    }

    @Override
    public DriverDto findById(Long id) {
        Driver driver = this.driverRepository.findById(id).get();
        return new DriverDto(
                driver.getUserId(),
                driver.getUsername(),
                driver.getUserEmail(),
                driver.getUserMobile(),
                driver.getUserImage(),
                driver.getVehicle().getVehicleId()
        );
    }

    @Override
    public Integer create(DriverDto driverDto) {
        return this.driverRepository.create(
                driverDto.getId(),
                driverDto.getVehId());
    }

    @Override
    public Integer edit(DriverDto driverDto) {
        return this.driverRepository.edit(
                driverDto.getId(),
                driverDto.getVehId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.driverRepository.deleteById(id);
    }
}
