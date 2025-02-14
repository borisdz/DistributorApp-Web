package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitSimpleDto;
import mk.ukim.finki.db.distributorapp.model.dto.OrderItemDto;
import mk.ukim.finki.db.distributorapp.repository.ArticleUnitRepository;
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

    @Override
    public List<ArticleUnitSimpleDto> getAllArticleUnits() {
        return this.articleUnitRepository.listAll();
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
    public void delete(Long id) {
        this.articleUnitRepository.deleteById(id);
    }

    @Override
    public List<ArticleUnitDto> getAllArticleUnitsByWarehouse(Integer warehouseId) {
        return this.articleUnitRepository.findAllByWarehouse(warehouseId);
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
