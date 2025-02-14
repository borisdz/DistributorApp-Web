package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.PriceDto;
import mk.ukim.finki.db.distributorapp.model.entities.Article;

import java.util.List;

public interface PriceService {

    Integer create(PriceDto priceDto);

    Integer edit(PriceDto priceDto);

    void deleteById(Integer id);

}
