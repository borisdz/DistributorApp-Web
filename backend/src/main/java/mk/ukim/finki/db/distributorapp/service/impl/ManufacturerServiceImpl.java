package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ManufacturerDto;
import mk.ukim.finki.db.distributorapp.model.entities.Manufacturer;
import mk.ukim.finki.db.distributorapp.repository.ManufacturerRepository;
import mk.ukim.finki.db.distributorapp.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;


    private List<ManufacturerDto> buildDto(List<Manufacturer> manufacturers) {
        List<ManufacturerDto> dtos = new ArrayList<>();
        for (Manufacturer manufacturer : manufacturers) {
            ManufacturerDto dto = new ManufacturerDto(
                    manufacturer.getManufacturerId(),
                    manufacturer.getManufacturerName(),
                    manufacturer.getManufacturerAddress(),
                    manufacturer.getManufacturerMobile(),
                    manufacturer.getManufacturerEmail()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<ManufacturerDto> getAllManufacturers() {
        List<Manufacturer> manufacturers = this.manufacturerRepository.listAll();
        return buildDto(manufacturers);
    }

    @Override
    public List<ManufacturerDto> findAllManufacturersByName(String name) {
        List<Manufacturer> manufacturers = this.manufacturerRepository.findAllByName("'"+name+"'");
        return buildDto(manufacturers);
    }

    @Override
    public ManufacturerDto findManufacturerById(Long id) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(id).get();
        return new ManufacturerDto(
                manufacturer.getManufacturerId(),
                manufacturer.getManufacturerName(),
                manufacturer.getManufacturerAddress(),
                manufacturer.getManufacturerMobile(),
                manufacturer.getManufacturerEmail()
        );
    }

    @Override
    public Integer create(ManufacturerDto manufacturerDto) {
        return this.manufacturerRepository.create(
                manufacturerDto.getName(),
                manufacturerDto.getAddress(),
                manufacturerDto.getPhone(),
                manufacturerDto.getEmail());
    }

    @Override
    public Integer edit(ManufacturerDto manufacturerDto) {
        return this.manufacturerRepository.edit(
                manufacturerDto.getId(),
                manufacturerDto.getName(),
                manufacturerDto.getAddress(),
                manufacturerDto.getPhone(),
                manufacturerDto.getEmail());
    }

    @Override
    public void deleteById(Long id) {

    }
}
