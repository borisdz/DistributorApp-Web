package mk.ukim.finki.db.distributorapp.delivery;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryStatusDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryStatusServiceImpl implements DeliveryStatusService {
    private final DeliveryStatusRepository deliveryStatusRepository;


    private List<DeliveryStatusDto> buildDto(List<DeliveryStatus> deliveryStatuses) {
        List<DeliveryStatusDto> dtos = new ArrayList<>();
        for (DeliveryStatus deliveryStatus : deliveryStatuses) {
            DeliveryStatusDto dto = new DeliveryStatusDto(
                    deliveryStatus.getDeliveryStatusId(),
                    deliveryStatus.getDeliveryStatusName(),
                    deliveryStatus.getDeliveryStatusDescription()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<DeliveryStatusDto> getAllDeliveryStatus() {
        List<DeliveryStatus> deliveryStatuses = this.deliveryStatusRepository.listAll();
        return buildDto(deliveryStatuses);
    }

    @Override
    public DeliveryStatusDto getDeliveryStatusById(Short id) {
        DeliveryStatus deliveryStatus = this.deliveryStatusRepository.findById(id).get();
        return new DeliveryStatusDto(
                deliveryStatus.getDeliveryStatusId(),
                deliveryStatus.getDeliveryStatusName(),
                deliveryStatus.getDeliveryStatusDescription()
        );
    }

    @Override
    public Integer create(DeliveryStatusDto deliveryStatusDto) {
        return this.deliveryStatusRepository.create(
                deliveryStatusDto.getStatusName(),
                deliveryStatusDto.getStatusDescription());
    }

    @Override
    public Integer edit(DeliveryStatusDto deliveryStatusDto) {
        return this.deliveryStatusRepository.edit(
                deliveryStatusDto.getId(),
                deliveryStatusDto.getStatusName(),
                deliveryStatusDto.getStatusDescription());
    }

    @Override
    public List<DeliveryStatusDto> getDeliveryStatusByName(String name) {
        List<DeliveryStatus> deliveryStatuses = this.deliveryStatusRepository.findAllByName("'"+name+"'");
        return buildDto(deliveryStatuses);
    }

    @Override
    public void deleteById(Short id) {
        this.deliveryStatusRepository.delete(id);
    }
}
