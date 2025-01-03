package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String name;
    private String manufacturer;
    private Long manufacturerId;
    private BigDecimal price;
    private String category;
    private Long categoryId;
    private Integer weight;
    private String image;
}
