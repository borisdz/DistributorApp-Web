package mk.ukim.finki.db.distributorapp.article;

import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getAllArticles();

    void deleteById(Long id);

    Integer editById(ArticleDto article);

    Integer create(ArticleDto article);

    List<ArticleDto> getAllArticlesByWarehouse(Integer warehouseId);

    ArticleDto findById(Long articleId, Integer warehouseId);

    List<ArticleDto> getArticlesByOrder(Long orderId);

    Page<ArticleDto> getArticlesPageable(
            Integer categoryId,
            Long manufacturerId,
            String nameFilter,
            int page,
            int size);
}