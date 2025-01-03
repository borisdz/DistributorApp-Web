package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUnitDto {
    private Long id;
    private Date expiryDate;
    private String serialNo;
    private String batchNo;
    private Date manufactureDate;
    private Double costPrice;
    private Long artId;
    private String artName;
    private Integer whId;
    private String whRegion;
    private String whCity;
    private Long ordId;
    private String customerEmail;
}
