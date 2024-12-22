package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> getAllManufacturers();
    List<Manufacturer> findAllManufacturersByName(String name);
    Optional<Manufacturer> findManufacturerById(Long id);
    Optional<Manufacturer> create(String name, String address, String mobile, String email);
    Optional<Manufacturer> edit(Long id, String name, String address, String mobile, String email);
    void delete(Long id);
}
