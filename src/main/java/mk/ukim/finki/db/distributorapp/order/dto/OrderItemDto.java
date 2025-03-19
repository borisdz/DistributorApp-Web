package mk.ukim.finki.db.distributorapp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private ArticleDto article;
    private Integer quantity;
}
