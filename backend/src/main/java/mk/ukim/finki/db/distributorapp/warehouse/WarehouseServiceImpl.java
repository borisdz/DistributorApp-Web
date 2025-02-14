package mk.ukim.finki.db.distributorapp.warehouse;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseInventoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public WarehouseDto findById(Integer id) {
        Warehouse wh = this.warehouseRepository.findById(id).get();
        return new WarehouseDto(
                wh.getWarehouseId(),
                wh.getWarehouseAddress(),
                wh.getCity().getCityId(),
                wh.getCity().getCityName(),
                wh.getCity().getRegion().getRegionId(),
                wh.getCity().getRegion().getRegionName()
        );
    }

    @Override
    public WarehouseDto findByCityId(Integer id) {
        return this.warehouseRepository.findWarehouseDtoByCityId(id);
    }

    @Override
    public Integer create(WarehouseDto warehouseDto) {
        return this.warehouseRepository.create(
                warehouseDto.getAddress(),
                warehouseDto.getCityId()
        );
    }

    @Override
    public Integer edit(WarehouseDto warehouseDto) {
        return this.warehouseRepository.edit(
                warehouseDto.getId(),
                warehouseDto.getAddress(),
                warehouseDto.getCityId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        this.warehouseRepository.findById(id);
    }

    @Override
    public List<WarehouseInventoryDto> getInventoryByManager(Long managerId) {
        return this.warehouseRepository.getInventoryByManager(managerId);
    }
}
