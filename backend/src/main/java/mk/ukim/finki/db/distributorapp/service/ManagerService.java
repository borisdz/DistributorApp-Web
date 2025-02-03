package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;

import java.util.List;

public interface ManagerService {

    List<ManagerDto> getAllManagers();

    ManagerDto getManagerById(Long id);

    Manager getManagerByIdObj(Long id);

    Integer create(ManagerDto managerDto);

    Integer edit(ManagerDto managerDto);

    void deleteById(Long id);

}
