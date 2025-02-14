package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.model.dto.WarehouseInventoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.City;

import java.util.List;

public interface WarehouseService {

    WarehouseDto findById(Integer id);

    WarehouseDto findByCityId(Integer id);

    Integer create(WarehouseDto warehouseDto);

    Integer edit(WarehouseDto warehouseDto);

    void deleteById(Integer id);

    List<WarehouseInventoryDto> getInventoryByManager(Long managerId);

    
}
