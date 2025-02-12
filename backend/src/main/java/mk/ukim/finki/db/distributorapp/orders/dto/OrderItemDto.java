package mk.ukim.finki.db.distributorapp.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long articleId;
    private String articleName;
    private String manufacturerName;
    private BigDecimal unitPrice;
    private Integer quantity;
}
