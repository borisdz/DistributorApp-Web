package mk.ukim.finki.db.distributorapp.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesForecast {
    private Long articleId;
    private String articleName;
    private Long currentStock;
    private BigDecimal averageUnitsSold;
    private BigDecimal remainingAfterForecast;
}
