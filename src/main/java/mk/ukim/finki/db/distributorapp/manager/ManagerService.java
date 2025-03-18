package mk.ukim.finki.db.distributorapp.manager;

import mk.ukim.finki.db.distributorapp.manager.dto.ManagerDto;

public interface ManagerService {

    Integer create(ManagerDto managerDto);

    Integer edit(ManagerDto managerDto);

    void deleteById(Long id);

}
