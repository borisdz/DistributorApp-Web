package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.entities.Region;

import java.util.List;
import java.util.Optional;

public interface RegionService {

    List<Region> listRegions();

    Optional<Region> getRegionById(Integer id);

    List<Region> searchRegions(String name);

    Optional<Region> createRegion(String name);

    Optional<Region> edit(Integer id, String name);

    void delete(Integer id);
}
