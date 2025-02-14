package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProFormaService {
    @Transactional
    List<ProFormaDto> getAllProForma();

    Integer create(ProFormaDto proFormaDto);

    Integer edit(ProFormaDto proFormaDto);

    void deleteById(Long id);

}
