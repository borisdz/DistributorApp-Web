package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.repository.ManagerRepository;
import mk.ukim.finki.db.distributorapp.service.ManagerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    private List<ManagerDto> buildDto(List<Manager> managers) {
        List<ManagerDto> dtos = new ArrayList<>();
        for (Manager manager : managers) {
            ManagerDto dto = new ManagerDto(
                    manager.getUserId(),
                    manager.getUsername(),
                    manager.getUserEmail(),
                    manager.getUserMobile(),
                    manager.getUserImage(),
                    manager.getWarehouse().getWarehouseId(),
                    manager.getWarehouse().getCity().getRegion().getRegionName(),
                    manager.getWarehouse().getCity().getCityName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<ManagerDto> getAllManagers() {
        List<Manager> managers = this.managerRepository.findAll();
        return buildDto(managers);
    }

    @Override
    public ManagerDto getManagerById(int id) {
        Manager manager = this.managerRepository.findById(id).get();

        return new ManagerDto(
                manager.getUserId(),
                manager.getUsername(),
                manager.getUserEmail(),
                manager.getUserMobile(),
                manager.getUserImage(),
                manager.getWarehouse().getWarehouseId(),
                manager.getWarehouse().getCity().getRegion().getRegionName(),
                manager.getWarehouse().getCity().getCityName()
        );
    }

    @Override
    public Integer create(ManagerDto managerDto) {
        return this.managerRepository.create(
                managerDto.getId(),
                managerDto.getWhId());
    }

    @Override
    public Integer edit(ManagerDto managerDto) {
        return this.managerRepository.edit(
                managerDto.getId(),
                managerDto.getWhId());
    }

    @Override
    public void deleteManagerById(Long id) {
        this.managerRepository.delete(id);
    }
}
