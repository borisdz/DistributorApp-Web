package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ManufacturerDto;
import mk.ukim.finki.db.distributorapp.repository.ManufacturerRepository;
import mk.ukim.finki.db.distributorapp.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Override
    public List<ManufacturerDto> getAllManufacturers() {
        return this.manufacturerRepository.listAll();
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
