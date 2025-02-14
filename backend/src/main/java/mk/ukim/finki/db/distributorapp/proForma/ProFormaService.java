package mk.ukim.finki.db.distributorapp.proForma;

import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProFormaService {
    @Transactional
    List<ProFormaDto> getAllProForma();

    Integer create(ProFormaDto proFormaDto);

    Integer edit(ProFormaDto proFormaDto);

    void deleteById(Long id);

}
