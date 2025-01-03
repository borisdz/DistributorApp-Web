package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.PriceDto;
import mk.ukim.finki.db.distributorapp.model.entities.Article;
import mk.ukim.finki.db.distributorapp.model.entities.Price;
import mk.ukim.finki.db.distributorapp.repository.PriceRepository;
import mk.ukim.finki.db.distributorapp.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    private List<PriceDto> buildDto(List<Price> prices) {
        List<PriceDto> dtos = new ArrayList<>();
        for (Price price : prices) {
            PriceDto dto = new PriceDto(
                    price.getPriceId(),
                    price.getPrice(),
                    price.getPriceEffectiveDate(),
                    price.getArticle().getArticleId(),
                    price.getArticle().getArticleName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<PriceDto> getAllPrices() {
        List<Price> prices = this.priceRepository.listAll();
        return buildDto(prices);
    }

    @Override
    public List<PriceDto> findAllPricesByArticleId(Article article) {
        List<Price> prices = this.priceRepository.findAllByArticleId(article.getArticleId());
        return buildDto(prices);
    }

    @Override
    public Integer create(PriceDto priceDto) {
        return this.priceRepository.create(
                priceDto.getPrice(),
                priceDto.getDateEffective(),
                priceDto.getArtId()
        );
    }

    @Override
    public Integer edit(PriceDto priceDto) {
        return this.priceRepository.edit(
                priceDto.getId(),
                priceDto.getPrice(),
                priceDto.getDateEffective(),
                priceDto.getArtId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        this.priceRepository.delete(id);
    }
}
