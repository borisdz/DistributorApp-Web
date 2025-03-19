package mk.ukim.finki.db.distributorapp.region;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.region.dto.RegionDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

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
