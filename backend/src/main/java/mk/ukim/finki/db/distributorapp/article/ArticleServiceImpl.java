package mk.ukim.finki.db.distributorapp.article;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleDto> getAllArticles() {
        return this.articleRepository.listAll();
    }

    @Override
    public ArticleDto findById(Long id) {
        Article art = this.articleRepository.findById(id).get();
        return new ArticleDto(
                art.getArticleId(),
                art.getArticleName(),
                art.getManufacturer().getManufacturerName(),
                0L,
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
                0L,
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
