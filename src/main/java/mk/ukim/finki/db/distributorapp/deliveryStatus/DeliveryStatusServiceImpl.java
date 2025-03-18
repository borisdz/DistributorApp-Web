package mk.ukim.finki.db.distributorapp.deliveryStatus;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.deliveryStatus.dto.DeliveryStatusDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryStatusServiceImpl implements DeliveryStatusService {
    private final DeliveryStatusRepository deliveryStatusRepository;

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
    public void deleteById(Short id) {
        this.deliveryStatusRepository.delete(id);
    }
}
