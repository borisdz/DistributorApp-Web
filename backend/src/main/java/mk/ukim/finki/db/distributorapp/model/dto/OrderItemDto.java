package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemDto {
    private Long articleId;
    private String articleName;
    private BigDecimal unitPrice;
    private Integer quantity;
}
