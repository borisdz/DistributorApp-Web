package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.RegionDto;

import java.util.List;

public interface RegionService {

    List<RegionDto> getAllRegions();

    RegionDto getRegionById(Integer id);

    List<RegionDto> searchRegions(String name);

    Integer create(RegionDto regionDto );

    Integer edit(RegionDto regionDto);

    RegionDto getRegionByCityId(Integer cityId);

    void deleteById(Integer id);
}
