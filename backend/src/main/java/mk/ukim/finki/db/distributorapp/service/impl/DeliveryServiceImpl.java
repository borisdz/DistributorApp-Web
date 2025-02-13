package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Delivery;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;
import mk.ukim.finki.db.distributorapp.repository.DeliveryRepository;
import mk.ukim.finki.db.distributorapp.service.DeliveryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    private List<DeliveryDto> buildDto(List<Delivery> deliveries) {
        List<DeliveryDto> dtos = new ArrayList<>();
        for (Delivery del : deliveries) {
            DeliveryDto dto = new DeliveryDto(
                    del.getDeliveryId(),
                    del.getDeliveryDateCreated(),
                    del.getDeliveryDate(),
                    del.getDeliveryStartKm(),
                    del.getDeliveryEndKm(),
                    null,
                    null,
                    del.getDeliveryStatus().getDeliveryStatusId(),
                    del.getDeliveryStatus().getDeliveryStatusName(),
                    del.getVehicle().getVehicleId(),
                    del.getVehicle().getDriver().getUserId(),
                    del.getVehicle().getDriver().getUsername(),
                    del.getVehicle().getDriver().getUserImage()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<DeliveryDto> getAllDeliveries() {
        List<Delivery> deliveries = this.deliveryRepository.listAll();
        return buildDto(deliveries);
    }

    @Override
    public List<DeliveryDto> getAllDeliveriesByVehicleId(Vehicle vehicle) {
        List<Delivery> deliveries = this.deliveryRepository.findAllByVehicle(vehicle.getVehicleId());
        return buildDto(deliveries);
    }

    @Override
    public List<DeliveryDto> getAllDeliveriesByDriver(Driver driver) {
        List<Delivery> deliveries = this.deliveryRepository.findDeliveriesByDriver(driver.getUserId());
        return buildDto(deliveries);
    }

    @Override
    public DeliveryFullDto findDeliveryById(Long id) {
        return this.deliveryRepository.findDeliveryDtoById(id);
    }

    @Override
    public Integer create(DeliveryCreateDto deliveryDto) {
        Date currentDate = new Date();
        return this.deliveryRepository.create(
                currentDate,
                deliveryDto.getDelDate(),
                null,
                null,
                null,
                null,
                (short) 1,
                deliveryDto.getVehId()
        );
    }

    @Override
    public Integer edit(DeliveryFullDto deliveryDto) {
        return this.deliveryRepository.edit(
                deliveryDto.getDelId(),
                deliveryDto.getDelDateCreated(),
                deliveryDto.getDelDate(),
                deliveryDto.getDelStartKm(),
                deliveryDto.getDelEndKm(),
                deliveryDto.getParsedDelStartTime(),
                deliveryDto.getParsedDelEndTime(),
                deliveryDto.getDelStatusId(),
                deliveryDto.getVeh_id()
        );
    }

    @Override
    public void deleteById(Long del_id) {
        this.deliveryRepository.delete(del_id);
    }

    @Override
    public List<DeliveryDto> getCurrentDeliveriesByCustomer(Long customerId) {
        return this.deliveryRepository.getCurrentDeliveriesByCustomer(customerId);
    }

    @Override
    public List<DeliveryDto> getCurrentDeliveriesByManager(Long managerId) {
        return this.deliveryRepository.getCurrentDeliveriesByManager(managerId);
    }

    @Override
    public List<DeliverySimpleDto> getDeliveriesByVehicle(Integer vehicleId) {
        return this.deliveryRepository.getDeliveriesByVehicle(vehicleId);
    }
}
