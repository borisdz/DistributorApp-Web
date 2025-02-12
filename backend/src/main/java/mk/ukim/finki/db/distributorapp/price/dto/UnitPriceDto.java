package mk.ukim.finki.db.distributorapp.price.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitPriceDto {
    private Long unitId;
    private Long priceId;
}
