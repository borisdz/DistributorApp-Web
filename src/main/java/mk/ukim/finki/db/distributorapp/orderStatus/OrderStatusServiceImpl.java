package mk.ukim.finki.db.distributorapp.orderStatus;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.orderStatus.dto.OrderStatusDto;
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
