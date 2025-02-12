package mk.ukim.finki.db.distributorapp.manufacturer;

import mk.ukim.finki.db.distributorapp.manufacturer.dto.ManufacturerDto;

import java.util.List;

public interface ManufacturerService {
    List<ManufacturerDto> getAllManufacturers();

    List<ManufacturerDto> findAllManufacturersByName(String name);

    ManufacturerDto findManufacturerById(Long id);

    Integer create(ManufacturerDto manufacturerDto);

    Integer edit(ManufacturerDto manufacturerDto);

    void deleteById(Long id);
}
