package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Article;
import mk.ukim.finki.db.distributorapp.model.entities.Category;
import mk.ukim.finki.db.distributorapp.model.entities.Manufacturer;
import mk.ukim.finki.db.distributorapp.repository.ArticleRepository;
import mk.ukim.finki.db.distributorapp.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return this.articleRepository.listAll();
    }

    @Override
    public List<ArticleDto> getAllArticlesDto() {
        List<Article> articles = this.articleRepository.listAll();
        return articles.stream().map(art->new ArticleDto(
                art.getArticleId(),
                art.getArticleName(),
                art.getManufacturer().getManufacturerName(),
                art.getCategory().getCategoryName(),
                art.getArticleWeight(),
                art.getArtImage()
        )).toList();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return this.articleRepository.findById(id);
    }

    @Override
    public List<Article> findAllByName(String name) {
        return this.articleRepository.findAllByName(name);
    }

    @Override
    public void deleteById(Long id) {
        this.articleRepository.deleteById(id);
    }

    @Override
    public Optional<Article> editById(Article article) {
        return this.articleRepository.edit(
                article.getArticleId(),
                article.getArticleName(),
                article.getArtImage(),
                article.getArticleWeight(),
                article.getCategory().getCategoryId(),
                article.getManufacturer().getManufacturerId());
    }

    @Override
    public Optional<Article> create(String name, String image, Integer weight, Category category, Manufacturer manufacturer) {
        return this.articleRepository.create(
                name,
                image,
                weight,
                category.getCategoryId(),
                manufacturer.getManufacturerId());
    }
}
