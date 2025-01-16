package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.model.entities.City;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;

import java.util.List;

public interface WarehouseService {
    List<WarehouseDto> getAllWarehouses();

    List<WarehouseDto> findAllByCity(City city);

    WarehouseDto findById(Integer id);

    Integer create(WarehouseDto warehouseDto);

    Integer edit(WarehouseDto warehouseDto);

    void deleteById(Integer id);

    List<WarehouseDto> getInventoryByManager(Manager manager);

    
}
