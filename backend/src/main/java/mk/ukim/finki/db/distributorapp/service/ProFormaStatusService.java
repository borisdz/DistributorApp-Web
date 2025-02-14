package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ProFormaStatusDto;

public interface ProFormaStatusService {

    Integer create(ProFormaStatusDto proFormaStatusDto);

    Integer edit (ProFormaStatusDto proFormaStatusDto );

    void deleteById(Short id);
}
