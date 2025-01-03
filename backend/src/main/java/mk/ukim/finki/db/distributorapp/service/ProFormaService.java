package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaDto;

import java.util.List;

public interface ProFormaService {
    List<ProFormaDto> getAllProForma();

    ProFormaDto findProFormaById(Long id);

    Integer create(ProFormaDto proFormaDto);

    Integer edit(ProFormaDto proFormaDto);

    void deleteById(Long id);

}
