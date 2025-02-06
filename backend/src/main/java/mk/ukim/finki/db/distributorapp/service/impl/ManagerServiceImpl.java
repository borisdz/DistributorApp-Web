package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.repository.ManagerRepository;
import mk.ukim.finki.db.distributorapp.service.ManagerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;


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
    public ManagerDto getManagerById(Long id) {
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

    public Manager getManagerByIdObj(Long id){
        return this.managerRepository.findById(id).get();
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
    public void deleteById(Long id) {
        this.managerRepository.delete(id);
    }
}
