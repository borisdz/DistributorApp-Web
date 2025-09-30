package mk.ukim.finki.db.distributorapp.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseYearlyFinancials {
    private Long warehouseId;
    private String warehouseCity;
    private String warehouseRegion;
    private Double year;
    private BigDecimal totalIncome;
    private BigDecimal totalProfit;
}
