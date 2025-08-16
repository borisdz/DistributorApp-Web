package mk.ukim.finki.db.distributorapp.article;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public ArticleDto findById(Long articleId, Integer warehouseId) {
        return this.articleRepository.findArticleDtoById(articleId, warehouseId);
    }

    @Override
    public List<ArticleDto> getArticlesByOrder(Long orderId) {
        return this.articleRepository.getArticlesByOrder(orderId);
    }

    @Override
    public Page<ArticleDto> getArticlesPageable(Integer categoryId, Long manufacturerId, String nameFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("art_name").ascending());
        return this.articleRepository.findAllWithFiltersPageable(categoryId,manufacturerId,nameFilter,pageable);
    }
}
