package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.OrderStatusDto;
import mk.ukim.finki.db.distributorapp.repository.OrderStatusRepository;
import mk.ukim.finki.db.distributorapp.service.OrderStatusService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public Integer create(OrderStatusDto orderStatusDto) {
        return this.orderStatusRepository.create(
                orderStatusDto.getStatusName(),
                orderStatusDto.getStatusDescription()
        );
    }

    @Override
    public Integer edit(OrderStatusDto orderStatusDto) {
        return this.orderStatusRepository.edit(
                orderStatusDto.getId(),
                orderStatusDto.getStatusName(),
                orderStatusDto.getStatusDescription()
        );
    }

    @Override
    public void deleteById(Short id) {
        this.orderStatusRepository.deleteById(id);
    }
}
