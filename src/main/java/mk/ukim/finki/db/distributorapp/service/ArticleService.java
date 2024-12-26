package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Category;
import mk.ukim.finki.db.distributorapp.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllArticles();

    Optional<Article> findById(Long id);

    List<Article> findAllByName(String name);

    void deleteById(Long id);

    Optional<Article> editById(Article article);

    Optional<Article> create(String name, Integer weight, Category category, Manufacturer manufacturer);
}
