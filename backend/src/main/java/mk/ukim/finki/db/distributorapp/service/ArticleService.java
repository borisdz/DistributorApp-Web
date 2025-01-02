package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Article;
import mk.ukim.finki.db.distributorapp.model.entities.Category;
import mk.ukim.finki.db.distributorapp.model.entities.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllArticles();

    List<ArticleDto> getAllArticlesDto();

    Optional<Article> findById(Long id);

    List<Article> findAllByName(String name);

    void deleteById(Long id);

    Optional<Article> editById(Article article);

    Optional<Article> create(String name, String image, Integer weight, Category category, Manufacturer manufacturer);
}
