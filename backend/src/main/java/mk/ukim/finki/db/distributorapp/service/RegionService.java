package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.RegionDto;

public interface RegionService {

    Integer create(RegionDto regionDto );

    Integer edit(RegionDto regionDto);

    void deleteById(Integer id);
}
