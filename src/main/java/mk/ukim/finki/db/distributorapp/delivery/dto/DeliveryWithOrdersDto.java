package mk.ukim.finki.db.distributorapp.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.order.dto.OrdersDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryWithOrdersDto {
    DeliveryFullDto delivery;
    List<OrdersDto> orders;
}
