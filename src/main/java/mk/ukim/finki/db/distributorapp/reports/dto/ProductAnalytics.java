package mk.ukim.finki.db.distributorapp.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAnalytics {
    private Long articleId;
    private String articleName;
    private String category;
    private Long totalUnitsSold;
    private BigDecimal totalIncome;
    private BigDecimal totalCost;
    private BigDecimal totalProfit;
}
