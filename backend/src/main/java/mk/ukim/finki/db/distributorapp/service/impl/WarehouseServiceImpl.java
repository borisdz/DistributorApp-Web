package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.model.entities.City;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.WarehouseRepository;
import mk.ukim.finki.db.distributorapp.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    private List<WarehouseDto> buildDtoList(List<Warehouse> warehouses) {
        List<WarehouseDto> dtos = new ArrayList<>();
        for (Warehouse wh : warehouses) {
            WarehouseDto dto = new WarehouseDto(
                    wh.getWarehouseId(),
                    wh.getWarehouseAddress(),
                    wh.getCity().getCityId(),
                    wh.getCity().getCityName(),
                    wh.getCity().getRegion().getRegionId(),
                    wh.getCity().getRegion().getRegionName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<WarehouseDto> getAllWarehouses() {
        List<Warehouse> warehouses = this.warehouseRepository.findAll();
        return buildDtoList(warehouses);
    }

    @Override
    public List<WarehouseDto> findAllByCity(City city) {
        List<Warehouse> warehouses = this.warehouseRepository.findAllByCity(city.getCityId());
        return buildDtoList(warehouses);
    }

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
    public List<WarehouseDto> getInventoryByManager(Manager manager) {
        return List.of();
    }
}
