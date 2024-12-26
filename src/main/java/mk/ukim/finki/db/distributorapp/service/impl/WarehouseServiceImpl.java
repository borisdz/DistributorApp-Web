package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.City;
import mk.ukim.finki.db.distributorapp.model.Warehouse;
import mk.ukim.finki.db.distributorapp.repository.WarehouseRepository;
import mk.ukim.finki.db.distributorapp.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> findAll() {
        return this.warehouseRepository.findAll();
    }

    @Override
    public List<Warehouse> findAllByCity(City city) {
        return this.warehouseRepository.findAllByCity(city.getCity_id());
    }

    @Override
    public Optional<Warehouse> findById(Integer id) {
        return this.warehouseRepository.findById(id);
    }

    @Override
    public Optional<Warehouse> create(String whAddress, City city) {
        return this.warehouseRepository.create(whAddress, city.getCity_id());
    }

    @Override
    public Optional<Warehouse> edit(Long id, String whAddress, City city) {
        return this.warehouseRepository.edit(id, whAddress, city.getCity_id());
    }

    @Override
    public Optional<Warehouse> delete(Integer id) {
        return this.warehouseRepository.findById(id);
    }
}
