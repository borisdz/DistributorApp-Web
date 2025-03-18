package mk.ukim.finki.db.distributorapp.warehouse;

import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseInventoryDto;

import java.util.List;

public interface WarehouseService {

    WarehouseDto findById(Integer id);

    WarehouseDto findByCityId(Integer id);

    Integer create(WarehouseDto warehouseDto);

    Integer edit(WarehouseDto warehouseDto);

    void deleteById(Integer id);

    List<WarehouseInventoryDto> getInventoryByManager(Long managerId);


}
