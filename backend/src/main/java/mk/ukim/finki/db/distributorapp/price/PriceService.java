package mk.ukim.finki.db.distributorapp.price;

import mk.ukim.finki.db.distributorapp.price.dto.PriceDto;
import mk.ukim.finki.db.distributorapp.article.Article;

import java.util.List;

public interface PriceService {
    List<PriceDto> getAllPrices();

    List<PriceDto> findAllPricesByArticleId(Article article);

    Integer create(PriceDto priceDto);

    Integer edit(PriceDto priceDto);

    void deleteById(Integer id);

}
