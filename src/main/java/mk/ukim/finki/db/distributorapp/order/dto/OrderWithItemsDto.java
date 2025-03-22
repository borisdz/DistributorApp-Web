package mk.ukim.finki.db.distributorapp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithItemsDto {
    private OrderSimpleDto order;
    private List<ArticleDto> items;
}
