package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaStatusDto;

import java.util.List;

public interface ProFormaStatusService {
    List<ProFormaStatusDto> listProFormaStatus();

    ProFormaStatusDto getProFormaStatusById(Short id);

    Integer create(ProFormaStatusDto proFormaStatusDto);

    Integer edit (ProFormaStatusDto proFormaStatusDto );

    List<ProFormaStatusDto> getProFormaStatusByName(String name);

    void delete(Short id);
}
