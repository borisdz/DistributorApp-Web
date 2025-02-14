package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitSimpleDto;
import mk.ukim.finki.db.distributorapp.model.dto.OrderItemDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleUnitService {
    List<ArticleUnitSimpleDto> getAllArticleUnits();

    Integer create(ArticleUnitDto articleUnitDto);

    Integer edit(ArticleUnitDto articleUnitDto);
    // TODO: Change edit and simpleEdit method
    @Transactional
    Integer simpleEdit(ArticleUnitSimpleDto articleUnitSimpleDto);

    void delete(Long id);

    List<ArticleUnitDto> getAllArticleUnitsByWarehouse(Integer warehouseId);

    List<ArticleUnitSimpleDto> findAllSimpleByArticleAndWarehouse(Long articleId, Integer warehouseId);

    List<ArticleUnitSimpleDto> addArticleUnitToOrder(List<OrderItemDto> orderItems, Long id, Integer whId);
}