package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Price;
import mk.ukim.finki.db.distributorapp.repository.PriceRepository;
import mk.ukim.finki.db.distributorapp.service.PriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getAllPrices() {
        return this.priceRepository.listAll();
    }

    @Override
    public List<Price> findAllPricesByArticleId(Article article) {
        return this.priceRepository.findAllByArticleId(article.getArticleId());
    }

    @Override
    public Optional<Price> create(BigDecimal price, LocalDateTime price_eff_date, Article article) {
        return this.priceRepository.create(price, price_eff_date, article.getArticleId());
    }

    @Override
    public Optional<Price> edit(Integer id, BigDecimal price, LocalDateTime price_eff_date, Article article) {
        return this.priceRepository.edit(
                id,
                price,
                price_eff_date,
                article.getArticleId());
    }

    @Override
    public void delete(Integer id) {
        this.priceRepository.delete(id);
    }
}
