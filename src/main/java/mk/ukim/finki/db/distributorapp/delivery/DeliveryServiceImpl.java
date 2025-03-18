package mk.ukim.finki.db.distributorapp.delivery;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

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
