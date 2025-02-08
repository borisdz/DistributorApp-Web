package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.model.dto.UnitPriceDto;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;

import java.util.List;

public interface ArticleUnitService {
    List<ArticleUnitDto> getAllArticleUnits();

    List<ArticleUnitDto> findALlByName(String name);

    List<ArticleUnitDto> findAllByWarehouse(Warehouse warehouse);

    ArticleUnitDto findById(Long id);

    Integer create(ArticleUnitDto articleUnitDto);

    Integer edit(ArticleUnitDto articleUnitDto);

    Integer addArticleUnitWithPrice(ArticleUnitDto articleUnitDto, UnitPriceDto unitPriceDto);

    void delete(Long id);

    List<ArticleUnitDto> getAllArticleUnitsByWarehouse(Integer warehouseId);
}