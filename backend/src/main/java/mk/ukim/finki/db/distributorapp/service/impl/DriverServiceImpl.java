package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.*;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.repository.DriverRepository;
import mk.ukim.finki.db.distributorapp.service.DeliveryService;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DeliveryService deliveryService;

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
    public Driver getDriverObjById(Long id) {
        return this.driverRepository.findById(id).get();
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

    @Override
    public List<DeliverySimpleDto> getNewAssignedDeliveries(Long driverId) {
        return this.driverRepository.activeAssignedDeliveries(driverId);
    }

    @Override
    public List<DeliverySimpleDto> getFinishedAssignedDeliveries(Long driverId) {
        return this.driverRepository.finishedAssignedDeliveries(driverId);
    }

    @Override
    public List<DeliverySimpleDto> getOngoingDeliveries(Long driverId) {
        return this.driverRepository.getOngoingDeliveries(driverId);
    }

    @Override
    @Transactional
    public void startDelivery(DeliveryStartDto delivery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        DeliveryFullDto deliveryDto = this.deliveryService.findDeliveryById(delivery.getId());
        deliveryDto.setDelStartKm(delivery.getDelStartKm());
        deliveryDto.setDelStartTime(LocalTime.now().format(formatter));
        deliveryDto.setDelStatusId((short)3);
        this.deliveryService.edit(deliveryDto);
    }

    @Override
    @Transactional
    public void endDelivery(DeliveryEndDto delivery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        DeliveryFullDto deliveryDto = this.deliveryService.findDeliveryById(delivery.getId());
        deliveryDto.setDelEndKm(delivery.getDelEndKm());
        deliveryDto.setDelEndTime(LocalTime.now().format(formatter));
        deliveryDto.setDelStatusId((short)4);
        this.deliveryService.edit(deliveryDto);
    }
}
