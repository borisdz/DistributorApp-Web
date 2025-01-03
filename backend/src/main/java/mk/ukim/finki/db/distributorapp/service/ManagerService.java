package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getAllManagers();

    ManagerDto getManagerById(int id);

    Integer create(ManagerDto managerDto);

    Integer edit(ManagerDto managerDto);

    void deleteManagerById(Long id);

}
