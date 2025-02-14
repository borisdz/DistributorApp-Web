package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.repository.ManagerRepository;
import mk.ukim.finki.db.distributorapp.service.ManagerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public Integer create(ManagerDto managerDto) {
        return this.managerRepository.create(
                managerDto.getId(),
                managerDto.getWhId()
        );
    }

    @Override
    public Integer edit(ManagerDto managerDto) {
        return this.managerRepository.edit(
                managerDto.getId(),
                managerDto.getWhId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.managerRepository.delete(id);
    }
}
