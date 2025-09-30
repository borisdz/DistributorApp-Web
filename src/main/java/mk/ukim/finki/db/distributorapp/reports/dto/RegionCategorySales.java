package mk.ukim.finki.db.distributorapp.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionCategorySales {
    private String region;
    private String category;
    private Long totalUnitsSold;
    private BigDecimal totalIncome;
}
