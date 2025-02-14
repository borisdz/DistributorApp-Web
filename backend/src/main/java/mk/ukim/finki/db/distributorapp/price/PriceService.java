package mk.ukim.finki.db.distributorapp.price;

import mk.ukim.finki.db.distributorapp.price.dto.PriceDto;

public interface PriceService {

    Integer create(PriceDto priceDto);

    Integer edit(PriceDto priceDto);

    void deleteById(Integer id);

}
