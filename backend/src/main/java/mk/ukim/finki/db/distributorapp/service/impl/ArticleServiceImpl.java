package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Article;
import mk.ukim.finki.db.distributorapp.repository.ArticleRepository;
import mk.ukim.finki.db.distributorapp.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleDto> getAllArticles() {
        List<Article> articles = this.articleRepository.listAll();
        return articles.stream().map(art -> new ArticleDto(
                art.getArticleId(),
                art.getArticleName(),
                art.getManufacturer().getManufacturerName(),
                art.getManufacturer().getManufacturerId(),
                art.getPrices().get(art.getPrices().size() - 1).getPrice(),
                art.getCategory().getCategoryName(),
                art.getCategory().getCategoryId(),
                art.getArticleWeight(),
                art.getArtImage()
        )).toList();
    }

    @Override
    public ArticleDto findById(Long id) {
        Article art = this.articleRepository.findById(id).get();
        return new ArticleDto(
                art.getArticleId(),
                art.getArticleName(),
                art.getManufacturer().getManufacturerName(),
                art.getManufacturer().getManufacturerId(),
                art.getPrices().get(art.getPrices().size() - 1).getPrice(),
                art.getCategory().getCategoryName(),
                art.getCategory().getCategoryId(),
                art.getArticleWeight(),
                art.getArtImage()
        );
    }

    @Override
    public List<ArticleDto> findAllByName(String name) {
        List<Article> articles = this.articleRepository.findAllByName("'" + name + "'");

        return articles.stream().map(art -> new ArticleDto(
                art.getArticleId(),
                art.getArticleName(),
                art.getManufacturer().getManufacturerName(),
                art.getManufacturer().getManufacturerId(),
                art.getPrices().get(art.getPrices().size() - 1).getPrice(),
                art.getCategory().getCategoryName(),
                art.getCategory().getCategoryId(),
                art.getArticleWeight(),
                art.getArtImage()
        )).toList();
    }

    @Override
    public void deleteById(Long id) {
        this.articleRepository.deleteById(id);
    }

    @Override
    public Integer editById(ArticleDto art) {
        return this.articleRepository.edit(
                art.getId(),
                art.getName(),
                art.getImage(),
                art.getWeight(),
                art.getCategoryId(),
                art.getManufacturerId());
    }

    @Override
    public Integer create(ArticleDto art) {
        return this.articleRepository.create(
                art.getName(),
                art.getImage(),
                art.getWeight(),
                art.getCategoryId(),
                art.getManufacturerId());
    }

    @Override
    public List<ArticleDto> getAllArticlesByWarehouse(Integer warehouseId) {
        return this.articleRepository.findAllByWarehouse(warehouseId);
    }
}
