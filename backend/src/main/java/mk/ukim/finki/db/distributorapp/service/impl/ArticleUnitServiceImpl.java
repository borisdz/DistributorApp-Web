package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.model.dto.UnitPriceDto;
import mk.ukim.finki.db.distributorapp.model.entities.ArticleUnit;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.ArticleUnitRepository;
import mk.ukim.finki.db.distributorapp.repository.PriceRepository;
import mk.ukim.finki.db.distributorapp.repository.UnitPriceRepository;
import mk.ukim.finki.db.distributorapp.repository.WarehouseRepository;
import mk.ukim.finki.db.distributorapp.service.ArticleUnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleUnitServiceImpl implements ArticleUnitService {
    private final ArticleUnitRepository articleUnitRepository;
    private final WarehouseRepository warehouseRepository;
    private final UnitPriceRepository unitPriceRepository;
    private final PriceRepository priceRepository;

    private List<ArticleUnitDto> buildDto(List<ArticleUnit> articleUnits) {
        List<ArticleUnitDto> res = new ArrayList<>();
        for (ArticleUnit unit : articleUnits) {
            Warehouse wh = this.warehouseRepository.findById(unit.getWarehouse().getWarehouseId()).get();
            ArticleUnitDto dto = new ArticleUnitDto(
                    unit.getUnitId(),
                    unit.getUnitExpirationDate(),
                    unit.getUnitSerialNumber(),
                    unit.getUnitBatchNumber(),
                    unit.getUnitManufactureDate(),
                    unit.getUnitCostPrice(),
                    unit.getArticle().getArticleId(),
                    unit.getArticle().getArticleName(),
                    wh.getWarehouseId(),
                    wh.getCity().getRegion().getRegionName(),
                    wh.getCity().getCityName(),
                    unit.getOrder().getOrderId(),
                    unit.getOrder().getCustomer().getUserEmail()
            );
            res.add(dto);
        }
        return res;
    }

    @Override
    public List<ArticleUnitDto> getAllArticleUnits() {
        List<ArticleUnit> articleUnits = this.articleUnitRepository.listAll();

        return buildDto(articleUnits);
    }

    @Override
    public List<ArticleUnitDto> findALlByName(String name) {
        List<ArticleUnit> articleUnits = this.articleUnitRepository.findAllByName("'" + name + "'");

        return buildDto(articleUnits);
    }

    @Override
    public List<ArticleUnitDto> findAllByWarehouse(Warehouse warehouse) {
        List<ArticleUnit> articleUnits = this.articleUnitRepository.findAllByWarehouse(warehouse.getWarehouseId());
        return buildDto(articleUnits);
    }

    @Override
    public ArticleUnitDto findById(Long id) {
        ArticleUnit articleUnit = this.articleUnitRepository.findById(id).get();
        Warehouse wh = this.warehouseRepository.findById(articleUnit.getWarehouse().getWarehouseId()).get();
        return new ArticleUnitDto(
                articleUnit.getUnitId(),
                articleUnit.getUnitExpirationDate(),
                articleUnit.getUnitSerialNumber(),
                articleUnit.getUnitBatchNumber(),
                articleUnit.getUnitManufactureDate(),
                articleUnit.getUnitCostPrice(),
                articleUnit.getArticle().getArticleId(),
                articleUnit.getArticle().getArticleName(),
                wh.getWarehouseId(),
                wh.getCity().getRegion().getRegionName(),
                wh.getCity().getCityName(),
                articleUnit.getOrder().getOrderId(),
                articleUnit.getOrder().getCustomer().getUserEmail()
        );
    }

    @Override
    @Transactional
    public Integer create(ArticleUnitDto articleUnitDto) {
        return this.articleUnitRepository.create(
                articleUnitDto.getExpiryDate(),
                articleUnitDto.getSerialNo(),
                articleUnitDto.getBatchNo(),
                articleUnitDto.getManufactureDate(),
                articleUnitDto.getCostPrice(),
                articleUnitDto.getArtId(),
                articleUnitDto.getWhId(),
                articleUnitDto.getOrdId()
        );
    }

    @Override
    @Transactional
    public Integer edit(ArticleUnitDto articleUnitDto) {
        return this.articleUnitRepository.edit(
                articleUnitDto.getId(),
                articleUnitDto.getExpiryDate(),
                articleUnitDto.getSerialNo(),
                articleUnitDto.getBatchNo(),
                articleUnitDto.getManufactureDate(),
                articleUnitDto.getCostPrice(),
                articleUnitDto.getArtId(),
                articleUnitDto.getWhId(),
                articleUnitDto.getOrdId()
        );
    }

    @Override
    @Transactional
    public Integer addArticleUnitWithPrice(ArticleUnitDto articleUnitDto, UnitPriceDto unitPriceDto) {
        this.articleUnitRepository.create(
                articleUnitDto.getExpiryDate(),
                articleUnitDto.getSerialNo(),
                articleUnitDto.getBatchNo(),
                articleUnitDto.getManufactureDate(),
                articleUnitDto.getCostPrice(),
                articleUnitDto.getArtId(),
                articleUnitDto.getWhId(),
                articleUnitDto.getOrdId()
        );
        this.unitPriceRepository.create(unitPriceDto.getUnitId(),unitPriceDto.getPriceId());

        return 0;
    }

    @Override
    public void delete(Long id) {
        this.articleUnitRepository.deleteById(id);
    }
}
