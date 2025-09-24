package mk.ukim.finki.db.distributorapp.discount;

import mk.ukim.finki.db.distributorapp.discount.dto.DiscountDto;
import org.springframework.stereotype.Service;

public interface DiscountService {
    Integer create(DiscountDto discountDto);
    Integer edit(DiscountDto discountDto);
    void deleteById(Long id);
}
