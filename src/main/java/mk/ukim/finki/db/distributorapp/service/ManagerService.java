package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Manager;
import mk.ukim.finki.db.distributorapp.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    List<Manager> getAllManagers();

    Optional<Manager> getManagerById(int id);

    Optional<Manager> createManager(Long id, Warehouse warehouse);

    Optional<Manager> updateManager(Manager manager, Warehouse warehouse);

    void deleteManagerById(Long id);

}
