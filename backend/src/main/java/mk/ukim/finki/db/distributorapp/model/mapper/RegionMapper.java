package mk.ukim.finki.db.distributorapp.model.mapper;

import mk.ukim.finki.db.distributorapp.model.dto.RegionDto;
import mk.ukim.finki.db.distributorapp.model.entities.Region;

public class RegionMapper {
    public static RegionDto toRegionDto(Region region) {
        RegionDto regionDto = new RegionDto();
        regionDto.setId(region.getRegionId());
        regionDto.setName(region.getRegionName());
        return regionDto;
    }
}
