package mk.ukim.finki.db.distributorapp.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ArticleSalesChange {
    private String articleName;
    private BigDecimal unitPrice;
    private Date priceEffectiveDate;
    private Long unitsSold;
    private BigDecimal revenue;
}
