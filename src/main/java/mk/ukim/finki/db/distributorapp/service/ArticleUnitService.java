package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Article_Unit;
import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.Warehouse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleUnitService {
    List<Article_Unit> getAllArticleUnits();

    List<Article_Unit> findALlByName(String name);

    List<Article_Unit> findAllByWarehouse(Warehouse warehouse);

    Optional<Article_Unit> findById(Long id);

    Optional<Article_Unit> create(
            Date unit_exp_date,
            String unit_ser_number,
            String unit_batch_number,
            Date unit_manufacture_date,
            Double unit_cost_price,
            Article article,
            Warehouse warehouse,
            Orders order
    );

    Optional<Article_Unit> edit(
            Long id,
            Date unit_exp_date,
            String unit_ser_number,
            String unit_batch_number,
            Date unit_manufacture_date,
            Double unit_cost_price,
            Article article,
            Warehouse warehouse,
            Orders order
    );

    void delete(Long id);
}
