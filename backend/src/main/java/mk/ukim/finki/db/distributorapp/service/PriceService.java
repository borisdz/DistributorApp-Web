package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceService {
    List<Price> getAllPrices();

    List<Price> findAllPricesByArticleId(Article article);

    Optional<Price> create(BigDecimal price, LocalDateTime price_eff_date, Article article);

    Optional<Price> edit(Integer id, BigDecimal price, LocalDateTime price_eff_date, Article article);

    void delete(Integer id);

}
