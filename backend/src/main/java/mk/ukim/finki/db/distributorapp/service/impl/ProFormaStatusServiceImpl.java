package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ProFormaStatusDto;
import mk.ukim.finki.db.distributorapp.repository.ProFormaStatusRepository;
import mk.ukim.finki.db.distributorapp.service.ProFormaStatusService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProFormaStatusServiceImpl implements ProFormaStatusService {
    private final ProFormaStatusRepository proFormaStatusRepository;

    @Override
    public Integer create(ProFormaStatusDto proFormaStatusDto ) {
        return this.proFormaStatusRepository.create(
                proFormaStatusDto.getStatusName(),
                proFormaStatusDto.getStatusDescription()
        );
    }

    @Override
    public Integer edit(ProFormaStatusDto proFormaStatusDto ) {
        return this.proFormaStatusRepository.edit(
                proFormaStatusDto.getId(),
                proFormaStatusDto.getStatusName(),
                proFormaStatusDto.getStatusDescription()
        );
    }

    @Override
    public void deleteById(Short id) {
        this.proFormaStatusRepository.deleteById(id);
    }
}
