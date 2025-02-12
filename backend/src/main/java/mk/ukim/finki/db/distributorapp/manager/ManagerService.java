package mk.ukim.finki.db.distributorapp.manager;

import mk.ukim.finki.db.distributorapp.manager.dto.ManagerDto;

import java.util.List;

public interface ManagerService {

    List<ManagerDto> getAllManagers();

    ManagerDto getManagerById(Long id);

    Manager getManagerByIdObj(Long id);

    Integer create(ManagerDto managerDto);

    Integer edit(ManagerDto managerDto);

    void deleteById(Long id);

}
