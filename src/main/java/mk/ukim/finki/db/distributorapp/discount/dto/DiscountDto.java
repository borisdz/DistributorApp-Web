package mk.ukim.finki.db.distributorapp.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {
    private Long discountId;
    private Integer discountAmount;
}
