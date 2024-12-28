package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.City;
import mk.ukim.finki.db.distributorapp.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    List<Warehouse> findAll();

    List<Warehouse> findAllByCity(City city);

    Optional<Warehouse> findById(Integer id);

    Optional<Warehouse> create(String whAddress, City city);

    Optional<Warehouse> edit(Long id, String whAddress, City city);

    Optional<Warehouse> delete(Integer id);
}
