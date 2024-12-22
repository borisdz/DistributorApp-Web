package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Article_Unit;
import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.ArticleUnitRepository;
import mk.ukim.finki.db.distributorapp.service.ArticleUnitService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ArticleUnitServiceImpl implements ArticleUnitService {
    private final ArticleUnitRepository articleUnitRepository;

    public ArticleUnitServiceImpl(ArticleUnitRepository articleUnitRepository) {
        this.articleUnitRepository = articleUnitRepository;
    }

    @Override
    public List<Article_Unit> getAllArticleUnits() {
        return this.articleUnitRepository.listAll();
    }

    @Override
    public List<Article_Unit> findALlByName(String name) {
        return this.articleUnitRepository.findAllByName(name);
    }

    @Override
    public List<Article_Unit> findAllByWarehouse(Warehouse warehouse) {
        return this.articleUnitRepository.findAllByWarehouse(warehouse.getWarehouse_id());
    }

    @Override
    public Optional<Article_Unit> findById(Long id) {
        return this.articleUnitRepository.findById(id);
    }

    @Override
    public Optional<Article_Unit> create(Date unit_exp_date, String unit_ser_number, String unit_batch_number, Date unit_manufacture_date, Double unit_cost_price, Article article, Warehouse warehouse, Orders order) {
        return this.articleUnitRepository.create(
                unit_exp_date,
                unit_ser_number,
                unit_batch_number,
                unit_manufacture_date,
                unit_cost_price,
                article.getArticle_id(),
                warehouse.getWarehouse_id(),
                order.getOrder_id()
        );
    }

    @Override
    public Optional<Article_Unit> edit(Long id, Date unit_exp_date, String unit_ser_number, String unit_batch_number, Date unit_manufacture_date, Double unit_cost_price, Article article, Warehouse warehouse, Orders order) {
        return this.articleUnitRepository.edit(
                id,
                unit_exp_date,
                unit_ser_number,
                unit_batch_number,
                unit_manufacture_date,
                unit_cost_price,
                article.getArticle_id(),
                warehouse.getWarehouse_id(),
                order.getOrder_id()
        );
    }

    @Override
    public void delete(Long id) {
        this.articleUnitRepository.deleteById(id);
    }
}
