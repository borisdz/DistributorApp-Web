package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PriceDto {
    private Integer id;
    private BigDecimal price;
    private LocalDateTime dateEffective;
    private Long artId;
    private String artName;
}
