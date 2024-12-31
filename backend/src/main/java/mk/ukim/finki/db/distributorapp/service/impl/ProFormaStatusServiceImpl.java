package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.ProFormaStatus;
import mk.ukim.finki.db.distributorapp.repository.ProFormaStatusRepository;
import mk.ukim.finki.db.distributorapp.service.ProFormaStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProFormaStatusServiceImpl implements ProFormaStatusService {
    private final ProFormaStatusRepository proFormaStatusRepository;

    public ProFormaStatusServiceImpl(ProFormaStatusRepository proFormaStatusRepository) {
        this.proFormaStatusRepository = proFormaStatusRepository;
    }

    @Override
    public List<ProFormaStatus> listProFormaStatus() {
        return this.proFormaStatusRepository.findAll();
    }

    @Override
    public Optional<ProFormaStatus> getProFormaStatusById(Short id) {
        return this.proFormaStatusRepository.findById(id);
    }

    @Override
    public Optional<ProFormaStatus> createProFormaStatus(String name, String description) {
        return this.proFormaStatusRepository.create(name, description);
    }

    @Override
    public Optional<ProFormaStatus> updateProFormaStatus(Short id, String name, String description) {
        return this.proFormaStatusRepository.edit(id, name, description);
    }

    @Override
    public List<ProFormaStatus> getProFormaStatusByName(String name) {
        return this.proFormaStatusRepository.findAllByName(name);
    }

    @Override
    public void delete(Short id) {
        this.proFormaStatusRepository.deleteById(id);
    }
}
