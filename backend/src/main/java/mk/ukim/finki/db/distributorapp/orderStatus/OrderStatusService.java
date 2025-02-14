package mk.ukim.finki.db.distributorapp.orderStatus;

import mk.ukim.finki.db.distributorapp.orderStatus.dto.OrderStatusDto;

public interface OrderStatusService {

    Integer create(OrderStatusDto orderStatusDto);

    Integer edit(OrderStatusDto orderStatusDto);

    void deleteById(Short id);
}
