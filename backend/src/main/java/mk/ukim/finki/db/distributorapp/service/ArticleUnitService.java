package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitSimpleDto;
import mk.ukim.finki.db.distributorapp.model.dto.OrderItemDto;
import mk.ukim.finki.db.distributorapp.model.dto.UnitPriceDto;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleUnitService {
    List<ArticleUnitSimpleDto> getAllArticleUnits();

    List<ArticleUnitDto> findALlByName(String name);

    List<ArticleUnitDto> findAllByWarehouse(Warehouse warehouse);

    ArticleUnitDto findById(Long id);

    Integer create(ArticleUnitDto articleUnitDto);

    Integer edit(ArticleUnitDto articleUnitDto);

    @Transactional
    Integer simpleEdit(ArticleUnitSimpleDto articleUnitSimpleDto);

    Integer addArticleUnitWithPrice(ArticleUnitDto articleUnitDto, UnitPriceDto unitPriceDto);

    void delete(Long id);

    List<ArticleUnitDto> getAllArticleUnitsByWarehouse(Integer warehouseId);

    List<ArticleUnitDto> findAllByArticleAndWarehouse(Long articleId, Integer warehouseId);

    List<ArticleUnitSimpleDto> findAllSimpleByArticleAndWarehouse(Long articleId, Integer warehouseId);

    List<ArticleUnitSimpleDto> addArticleUnitToOrder(List<OrderItemDto> orderItems, Long id, Integer whId);
}