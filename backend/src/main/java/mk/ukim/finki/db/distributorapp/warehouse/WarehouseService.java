package mk.ukim.finki.db.distributorapp.warehouse;

import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseInventoryDto;
import mk.ukim.finki.db.distributorapp.city.City;

import java.util.List;

public interface WarehouseService {
    List<WarehouseDto> getAllWarehouses();

    List<WarehouseDto> findAllByCity(City city);

    WarehouseDto findById(Integer id);

    WarehouseDto findByCityId(Integer id);

    Integer create(WarehouseDto warehouseDto);

    Integer edit(WarehouseDto warehouseDto);

    void deleteById(Integer id);

    List<WarehouseInventoryDto> getInventoryByManager(Long managerId);

    
}
