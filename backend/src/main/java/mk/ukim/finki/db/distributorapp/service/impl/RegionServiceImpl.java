package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.entities.Region;
import mk.ukim.finki.db.distributorapp.repository.RegionRepository;
import mk.ukim.finki.db.distributorapp.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> listRegions() {
        return this.regionRepository.listAll();
    }

    @Override
    public Optional<Region> getRegionById(Integer id) {
        return this.regionRepository.findById(id);
    }

    @Override
    public List<Region> searchRegions(String name) {
        return this.regionRepository.findByName("'"+name+"'");
    }

    @Override
    public Optional<Region> createRegion(String name) {
        return this.regionRepository.create(name);
    }

    @Override
    public Optional<Region> edit(Integer id, String name) {
        return this.regionRepository.edit(id, name);
    }

    @Override
    public void delete(Integer id) {
        this.regionRepository.deleteById(id);
    }
}
