package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Manager;
import mk.ukim.finki.db.distributorapp.model.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.ManagerRepository;
import mk.ukim.finki.db.distributorapp.service.ManagerService;

import java.util.List;
import java.util.Optional;

public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> getAllManagers() {
        return this.managerRepository.findAll();
    }

    @Override
    public Optional<Manager> getManagerById(int id) {
        return this.managerRepository.findById(id);
    }

    @Override
    public Optional<Manager> createManager(Long id, Warehouse warehouse) {
        return this.managerRepository.create(id, warehouse.getWarehouse_id());
    }

    @Override
    public Optional<Manager> updateManager(Manager manager, Warehouse warehouse) {
        return this.managerRepository.edit(manager.getUser_id(),warehouse.getWarehouse_id());
    }

    @Override
    public void deleteManagerById(Long id) {
        this.managerRepository.delete(id);
    }
}
