package mk.ukim.finki.db.distributorapp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    private List<OrderItemDto> orderItems;
    boolean proForma;
}
