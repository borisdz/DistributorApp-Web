package mk.ukim.finki.db.distributorapp.proFormaStatus;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.proFormaStatus.dto.ProFormaStatusDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProFormaStatusServiceImpl implements ProFormaStatusService {
    private final ProFormaStatusRepository proFormaStatusRepository;

    @Override
    public Integer create(ProFormaStatusDto proFormaStatusDto) {
        return this.proFormaStatusRepository.create(
                proFormaStatusDto.getStatusName(),
                proFormaStatusDto.getStatusDescription()
        );
    }

    @Override
    public Integer edit(ProFormaStatusDto proFormaStatusDto) {
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
