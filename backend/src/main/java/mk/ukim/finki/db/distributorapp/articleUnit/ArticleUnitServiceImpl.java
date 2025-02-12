package mk.ukim.finki.db.distributorapp.articleUnit;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitSimpleDto;
import mk.ukim.finki.db.distributorapp.orders.dto.OrderItemDto;
import mk.ukim.finki.db.distributorapp.price.dto.UnitPriceDto;
import mk.ukim.finki.db.distributorapp.warehouse.Warehouse;
import mk.ukim.finki.db.distributorapp.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp.price.PriceRepository;
import mk.ukim.finki.db.distributorapp.price.UnitPriceRepository;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseRepository;
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
    public List<ArticleUnitSimpleDto> getAllArticleUnits() {
        return this.articleUnitRepository.listAll();

    }

    @Override
    public List<ArticleUnitDto> findALlByName(String name) {
        List<ArticleUnit> articleUnits = this.articleUnitRepository.findAllByName("'" + name + "'");

        return buildDto(articleUnits);
    }

    @Override
    public List<ArticleUnitDto> findAllByWarehouse(Warehouse warehouse) {
        return this.articleUnitRepository.findAllByWarehouse(warehouse.getWarehouseId());
    }

    @Override
    public ArticleUnitDto findById(Long id) {
        ArticleUnit articleUnit = this.articleUnitRepository.findById(id).orElseThrow(InvalidArgumentsException::new);
        Warehouse wh = this.warehouseRepository.findById(articleUnit.getWarehouse().getWarehouseId()).orElseThrow(InvalidArgumentsException::new);
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
                articleUnitDto.getWhId(),
                articleUnitDto.getOrdId()
        );
    }

    @Transactional
    @Override
    public Integer simpleEdit(ArticleUnitSimpleDto articleUnitSimpleDto){
        return this.articleUnitRepository.edit(
                articleUnitSimpleDto.getId(),
                articleUnitSimpleDto.getExpiryDate(),
                articleUnitSimpleDto.getSerialNo(),
                articleUnitSimpleDto.getBatchNo(),
                articleUnitSimpleDto.getManufactureDate(),
                articleUnitSimpleDto.getCostPrice(),
                articleUnitSimpleDto.getWhId(),
                articleUnitSimpleDto.getOrdId()
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

    @Override
    public List<ArticleUnitDto> getAllArticleUnitsByWarehouse(Integer warehouseId) {
        return this.articleUnitRepository.findAllByWarehouse(warehouseId);
    }

    @Override
    public List<ArticleUnitDto> findAllByArticleAndWarehouse(Long articleId, Integer warehouseId) {
        return this.articleUnitRepository.findAllByArticleAndWarehouse(articleId,warehouseId);
    }

    @Override
    public List<ArticleUnitSimpleDto> findAllSimpleByArticleAndWarehouse(Long articleId, Integer warehouseId){
        return this.articleUnitRepository.findAllSimpleByArticleAndWarehouse(articleId,warehouseId);
    }

    @Override
    public List<ArticleUnitSimpleDto> addArticleUnitToOrder(List<OrderItemDto> orderItems, Long id, Integer whId) {
        List<ArticleUnitSimpleDto> editedUnits = new ArrayList<>();
        for (OrderItemDto orderItem : orderItems) {
            Long articleId = orderItem.getArticleId();
            Integer quantity = orderItem.getQuantity();

            List<ArticleUnitSimpleDto> articleUnitItems = findAllSimpleByArticleAndWarehouse(articleId, whId);
            for (int j = 0; j < quantity; j++) {
                articleUnitItems.get(j).setOrdId(id);
                editedUnits.add(articleUnitItems.get(j));
            }
        }
        return editedUnits;
    }
}
