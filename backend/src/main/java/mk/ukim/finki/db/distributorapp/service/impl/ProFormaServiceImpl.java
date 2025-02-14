package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ProFormaDto;
import mk.ukim.finki.db.distributorapp.repository.ProFormaRepository;
import mk.ukim.finki.db.distributorapp.service.ProFormaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProFormaServiceImpl implements ProFormaService {
    private final ProFormaRepository proFormaRepository;

    @Override
    @Transactional
    public List<ProFormaDto> getAllProForma() {
        return this.proFormaRepository.listAll();
    }

    @Override
    public Integer create(ProFormaDto proFormaDto) {
        return this.proFormaRepository.create(
                proFormaDto.getPfDeadline(),
                proFormaDto.getPfDateCreated(),
                proFormaDto.getStatusId()
        );
    }

    @Override
    public Integer edit(ProFormaDto proFormaDto) {
        return this.proFormaRepository.edit(
                proFormaDto.getId(),
                proFormaDto.getPfDeadline(),
                proFormaDto.getPfDateCreated(),
                proFormaDto.getStatusId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.proFormaRepository.deleteById(id);
    }
}
