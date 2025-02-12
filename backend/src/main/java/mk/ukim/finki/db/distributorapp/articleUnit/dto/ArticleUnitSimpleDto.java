package mk.ukim.finki.db.distributorapp.articleUnit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUnitSimpleDto {
    Long id;
    Date expiryDate;
    String serialNo;
    String batchNo;
    Date manufactureDate;
    BigDecimal costPrice;
    Long artId;
    Integer whId;
    Long ordId;
}
