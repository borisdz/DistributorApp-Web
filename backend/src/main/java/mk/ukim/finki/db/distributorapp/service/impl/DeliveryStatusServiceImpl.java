package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.DeliveryStatusDto;
import mk.ukim.finki.db.distributorapp.model.entities.DeliveryStatus;
import mk.ukim.finki.db.distributorapp.repository.DeliveryStatusRepository;
import mk.ukim.finki.db.distributorapp.service.DeliveryStatusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryStatusServiceImpl implements DeliveryStatusService {
    private final DeliveryStatusRepository deliveryStatusRepository;

    public DeliveryStatusServiceImpl(DeliveryStatusRepository deliveryStatusRepository) {
        this.deliveryStatusRepository = deliveryStatusRepository;
    }

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
    public List<DeliveryStatusDto> listDeliveryStatus() {
        List<DeliveryStatus> deliveryStatuses = this.deliveryStatusRepository.findAll();
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
        List<DeliveryStatus> deliveryStatuses = this.deliveryStatusRepository.findAllByName(name);
        return buildDto(deliveryStatuses);
    }

    @Override
    public void delete(Short id) {
        this.deliveryStatusRepository.delete(id);
    }
}
