package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaStatusDto;

import java.util.List;

public interface ProFormaStatusService {
    List<ProFormaStatusDto> getAllProFormaStatus();

    ProFormaStatusDto getProFormaStatusById(Short id);

    Integer create(ProFormaStatusDto proFormaStatusDto);

    Integer edit (ProFormaStatusDto proFormaStatusDto );

    List<ProFormaStatusDto> getProFormaStatusByName(String name);

    void deleteById(Short id);
}
