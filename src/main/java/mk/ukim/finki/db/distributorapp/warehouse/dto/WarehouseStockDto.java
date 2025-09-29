package mk.ukim.finki.db.distributorapp.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseStockDto {
    private Long articleId;
    private String articleName;
    private String articleImage;
    private Double articleWeight;
    private Integer quantity;
    private Date nearestExpirationDate;
    private BigDecimal sellingPrice;
    private BigDecimal costPrice;
    private String manufacturerName;
    private String categoryName;
}
