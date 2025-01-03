package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaDto;

import java.util.List;

public interface ProFormaService {
    List<ProFormaDto> getAllPro_Forma();

    ProFormaDto findProFormaById(Long id);

    Integer create(ProFormaDto proFormaDto);

    Integer edit(ProFormaDto proFormaDto);

    void delete(Long id);

}
