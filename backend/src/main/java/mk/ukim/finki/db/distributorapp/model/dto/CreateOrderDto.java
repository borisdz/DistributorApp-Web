package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderDto {
    private List<OrderItemDto> orderItems;
    boolean proForma;
}
