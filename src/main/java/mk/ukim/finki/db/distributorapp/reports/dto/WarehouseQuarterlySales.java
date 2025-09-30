package mk.ukim.finki.db.distributorapp.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseQuarterlySales {
    private Integer warehouseId;
    private String warehouseAddress;
    private String warehouseCity;
    private String warehouseRegion;
    private Double year;
    private Double quarter;
    private Long totalArticlesSold;
    private BigDecimal totalSales;
}
