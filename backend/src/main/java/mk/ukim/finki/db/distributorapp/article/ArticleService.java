package mk.ukim.finki.db.distributorapp.article;

import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getAllArticles();

    ArticleDto findById(Long id);

    List<ArticleDto> findAllByName(String name);

    void deleteById(Long id);

    Integer editById(ArticleDto article);

    Integer create(ArticleDto article);

    List<ArticleDto> getAllArticlesByWarehouse(Integer warehouseId);
}