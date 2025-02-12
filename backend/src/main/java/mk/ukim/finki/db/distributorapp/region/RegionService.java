package mk.ukim.finki.db.distributorapp.region;

import mk.ukim.finki.db.distributorapp.region.dto.RegionDto;

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
