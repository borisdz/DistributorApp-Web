package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ProFormaStatusDto;
import mk.ukim.finki.db.distributorapp.model.entities.ProFormaStatus;
import mk.ukim.finki.db.distributorapp.repository.ProFormaStatusRepository;
import mk.ukim.finki.db.distributorapp.service.ProFormaStatusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProFormaStatusServiceImpl implements ProFormaStatusService {
    private final ProFormaStatusRepository proFormaStatusRepository;


    private List<ProFormaStatusDto> buildDto(List<ProFormaStatus> proFormaStatuses) {
        List<ProFormaStatusDto> proFormaStatusDtos = new ArrayList<>();
        for (ProFormaStatus proFormaStatus : proFormaStatuses) {
            ProFormaStatusDto proFormaStatusDto = new ProFormaStatusDto(
                    proFormaStatus.getProFormaStatusId(),
                    proFormaStatus.getProFormaStatusName(),
                    proFormaStatus.getProFormaStatusDescription()
            );
            proFormaStatusDtos.add(proFormaStatusDto);
        }
        return proFormaStatusDtos;
    }

    @Override
    public List<ProFormaStatusDto> getAllProFormaStatus() {
        List<ProFormaStatus> proFormaStatuses = this.proFormaStatusRepository.findAll();
        return buildDto(proFormaStatuses);
    }

    @Override
    public ProFormaStatusDto getProFormaStatusById(Short id) {
        ProFormaStatus status = this.proFormaStatusRepository.findById(id).get();
        return new ProFormaStatusDto(
                status.getProFormaStatusId(),
                status.getProFormaStatusName(),
                status.getProFormaStatusDescription()
        );
    }

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
    public List<ProFormaStatusDto> getProFormaStatusByName(String name) {
        List<ProFormaStatus> statuses = this.proFormaStatusRepository.findAllByName("'"+name+"'");
        return buildDto(statuses);
    }

    @Override
    public void deleteById(Short id) {
        this.proFormaStatusRepository.deleteById(id);
    }
}
