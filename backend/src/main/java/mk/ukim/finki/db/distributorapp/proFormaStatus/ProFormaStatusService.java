package mk.ukim.finki.db.distributorapp.proFormaStatus;

import mk.ukim.finki.db.distributorapp.proFormaStatus.dto.ProFormaStatusDto;

public interface ProFormaStatusService {

    Integer create(ProFormaStatusDto proFormaStatusDto);

    Integer edit(ProFormaStatusDto proFormaStatusDto);

    void deleteById(Short id);
}
