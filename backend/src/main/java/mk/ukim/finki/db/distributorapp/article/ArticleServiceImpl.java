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
