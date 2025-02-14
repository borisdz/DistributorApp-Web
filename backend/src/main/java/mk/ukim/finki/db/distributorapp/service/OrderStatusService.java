package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.OrderStatusDto;

public interface OrderStatusService {

    Integer create(OrderStatusDto orderStatusDto);

    Integer edit(OrderStatusDto orderStatusDto);

    void deleteById(Short id);
}
