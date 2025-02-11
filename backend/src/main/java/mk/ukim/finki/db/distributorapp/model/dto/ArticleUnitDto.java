package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class ArticleUnitDto {
    private Long id;
    private Date expiryDate;
    private String serialNo;
    private String batchNo;
    private Date manufactureDate;
    private BigDecimal costPrice;
    private Long artId;
    private String artName;
    private Integer whId;
    private String whRegion;
    private String whCity;
    private Long ordId;
    private String customerEmail;
}
