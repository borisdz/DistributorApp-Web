package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.ArticleUnit;
import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.Warehouse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleUnitService {
    List<ArticleUnit> getAllArticleUnits();

    List<ArticleUnit> findALlByName(String name);

    List<ArticleUnit> findAllByWarehouse(Warehouse warehouse);

    Optional<ArticleUnit> findById(Long id);

    Optional<ArticleUnit> create(
            Date unit_exp_date,
            String unit_ser_number,
            String unit_batch_number,
            Date unit_manufacture_date,
            Double unit_cost_price,
            Article article,
            Warehouse warehouse,
            Orders order
    );

    Optional<ArticleUnit> edit(
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
