package mk.ukim.finki.db.distributorapp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AndroidCreateOrderDto {
    Long articleId;
    BigDecimal unitPrice;
    Integer quantity;
    String userEmail;
    Boolean proForma;
}
