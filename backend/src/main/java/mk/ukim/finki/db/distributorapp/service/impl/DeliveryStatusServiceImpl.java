package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryStatusDto;
import mk.ukim.finki.db.distributorapp.repository.DeliveryStatusRepository;
import mk.ukim.finki.db.distributorapp.service.DeliveryStatusService;
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
