package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;

public interface ManagerService {

    Integer create(ManagerDto managerDto);

    Integer edit(ManagerDto managerDto);

    void deleteById(Long id);

}
