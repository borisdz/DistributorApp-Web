package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Category;
import mk.ukim.finki.db.distributorapp.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> findAll();

    Optional<Article> findById(long id);

    Optional<Article> findByName(String name);

    void deleteById(long id);

    Optional<Article>editById(long id, Article article);

    Optional<Article> save(Long id, String name, Integer weight, Category category, Manufacturer manufacturer);
}
