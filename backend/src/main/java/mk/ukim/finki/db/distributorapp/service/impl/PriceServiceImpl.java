package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.PriceDto;
import mk.ukim.finki.db.distributorapp.repository.PriceRepository;
import mk.ukim.finki.db.distributorapp.service.PriceService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

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
