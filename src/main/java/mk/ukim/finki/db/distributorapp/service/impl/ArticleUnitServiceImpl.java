package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.ArticleUnit;
import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.ArticleUnitRepository;
import mk.ukim.finki.db.distributorapp.service.ArticleUnitService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleUnitServiceImpl implements ArticleUnitService {
    private final ArticleUnitRepository articleUnitRepository;

    public ArticleUnitServiceImpl(ArticleUnitRepository articleUnitRepository) {
        this.articleUnitRepository = articleUnitRepository;
    }

    @Override
    public List<ArticleUnit> getAllArticleUnits() {
        return this.articleUnitRepository.listAll();
    }

    @Override
    public List<ArticleUnit> findALlByName(String name) {
        return this.articleUnitRepository.findAllByName(name);
    }

    @Override
    public List<ArticleUnit> findAllByWarehouse(Warehouse warehouse) {
        return this.articleUnitRepository.findAllByWarehouse(warehouse.getWarehouseId());
    }

    @Override
    public Optional<ArticleUnit> findById(Long id) {
        return this.articleUnitRepository.findById(id);
    }

    @Override
    public Optional<ArticleUnit> create(Date unit_exp_date, String unit_ser_number, String unit_batch_number, Date unit_manufacture_date, Double unit_cost_price, Article article, Warehouse warehouse, Orders order) {
        return this.articleUnitRepository.create(
                unit_exp_date,
                unit_ser_number,
                unit_batch_number,
                unit_manufacture_date,
                unit_cost_price,
                article.getArticleId(),
                warehouse.getWarehouseId(),
                order.getOrderId()
        );
    }

    @Override
    public Optional<ArticleUnit> edit(Long id, Date unit_exp_date, String unit_ser_number, String unit_batch_number, Date unit_manufacture_date, Double unit_cost_price, Article article, Warehouse warehouse, Orders order) {
        return this.articleUnitRepository.edit(
                id,
                unit_exp_date,
                unit_ser_number,
                unit_batch_number,
                unit_manufacture_date,
                unit_cost_price,
                article.getArticleId(),
                warehouse.getWarehouseId(),
                order.getOrderId()
        );
    }

    @Override
    public void delete(Long id) {
        this.articleUnitRepository.deleteById(id);
    }
}
