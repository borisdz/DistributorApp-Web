package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.entities.Manufacturer;
import mk.ukim.finki.db.distributorapp.repository.ManufacturerRepository;
import mk.ukim.finki.db.distributorapp.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return this.manufacturerRepository.listAll();
    }

    @Override
    public List<Manufacturer> findAllManufacturersByName(String name) {
        return this.manufacturerRepository.findAllByName(name);
    }

    @Override
    public Optional<Manufacturer> findManufacturerById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> create(String name, String address, String mobile, String email) {
        return this.manufacturerRepository.create(name, address, mobile, email);
    }

    @Override
    public Optional<Manufacturer> edit(Long id, String name, String address, String mobile, String email) {
        return this.manufacturerRepository.edit(id, name, address, mobile, email);
    }

    @Override
    public void delete(Long id) {

    }
}
