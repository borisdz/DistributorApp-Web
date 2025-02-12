package mk.ukim.finki.db.distributorapp.delivery;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.driver.Driver;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.vehicle.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    del.getDeliveryStartTime(),
                    del.getDeliveryEndTime(),
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
    public DeliveryDto findDeliveryById(Long id) {
        Delivery del = this.deliveryRepository.findById(id).get();
        return new DeliveryDto(
                del.getDeliveryId(),
                del.getDeliveryDateCreated(),
                del.getDeliveryDate(),
                del.getDeliveryStartKm(),
                del.getDeliveryEndKm(),
                del.getDeliveryStartTime(),
                del.getDeliveryEndTime(),
                del.getDeliveryStatus().getDeliveryStatusId(),
                del.getDeliveryStatus().getDeliveryStatusName(),
                del.getVehicle().getVehicleId(),
                del.getVehicle().getDriver().getUserId(),
                del.getVehicle().getDriver().getUsername(),
                del.getVehicle().getDriver().getUserImage()
        );
    }

    @Override
    public Integer create(DeliveryDto deliveryDto) {
        return this.deliveryRepository.create(
                deliveryDto.getDateCreated(),
                deliveryDto.getDelDate(),
                deliveryDto.getDelStartKm(),
                deliveryDto.getDelEndKm(),
                deliveryDto.getDelStartTime(),
                deliveryDto.getDelEndTime(),
                deliveryDto.getDStatusId(),
                deliveryDto.getVehId()
        );
    }

    @Override
    public Integer edit(DeliveryDto deliveryDto) {
        return this.deliveryRepository.edit(
                deliveryDto.getId(),
                deliveryDto.getDateCreated(),
                deliveryDto.getDelDate(),
                deliveryDto.getDelStartKm(),
                deliveryDto.getDelEndKm(),
                deliveryDto.getDelStartTime(),
                deliveryDto.getDelEndTime(),
                deliveryDto.getDStatusId(),
                deliveryDto.getVehId()
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
}
