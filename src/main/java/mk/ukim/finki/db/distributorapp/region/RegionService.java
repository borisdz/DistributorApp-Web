package mk.ukim.finki.db.distributorapp.region;

import mk.ukim.finki.db.distributorapp.region.dto.RegionDto;

public interface RegionService {

    Integer create(RegionDto regionDto);

    Integer edit(RegionDto regionDto);

    void deleteById(Integer id);
}
