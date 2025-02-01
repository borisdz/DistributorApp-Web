package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.RegionDto;
import mk.ukim.finki.db.distributorapp.model.entities.Region;
import mk.ukim.finki.db.distributorapp.repository.RegionRepository;
import mk.ukim.finki.db.distributorapp.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    private List<RegionDto> buildDto(List<Region> regions) {
        List<RegionDto> dtos = new ArrayList<>();
        for (Region region : regions) {
            RegionDto dto = new RegionDto(
                    region.getRegionId(),
                    region.getRegionName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<RegionDto> getAllRegions() {
        List<Region> regions = this.regionRepository.listAll();
        return buildDto(regions);
    }

    @Override
    public RegionDto getRegionById(Integer id) {
        Region region = this.regionRepository.findById(id).get();
        return new RegionDto(
                region.getRegionId(),
                region.getRegionName()
        );
    }

    @Override
    public List<RegionDto> searchRegions(String name) {
        List<Region> regions = this.regionRepository.findByName("'" + name + "'");
        return buildDto(regions);
    }

    @Override
    public Integer create(RegionDto regionDto) {
        return this.regionRepository.create(
                regionDto.getName()
        );
    }

    @Override
    public Integer edit(RegionDto regionDto) {
        return this.regionRepository.edit(
                regionDto.getId(),
                regionDto.getName()
        );
    }

    @Override
    public void deleteById(Integer id) {
        this.regionRepository.deleteById(id);
    }
}
