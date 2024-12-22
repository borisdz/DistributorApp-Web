package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.statuses.Pro_Forma_Status;
import mk.ukim.finki.db.distributorapp.repository.ProFormaStatusRepository;
import mk.ukim.finki.db.distributorapp.service.ProFormaStatusService;

import java.util.List;
import java.util.Optional;

public class ProFormaStatusServiceImpl implements ProFormaStatusService {
    private final ProFormaStatusRepository proFormaStatusRepository;

    public ProFormaStatusServiceImpl(ProFormaStatusRepository proFormaStatusRepository) {
        this.proFormaStatusRepository = proFormaStatusRepository;
    }

    @Override
    public List<Pro_Forma_Status> listProFormaStatus() {
        return this.proFormaStatusRepository.findAll();
    }

    @Override
    public Optional<Pro_Forma_Status> getProFormaStatusById(Short id) {
        return this.proFormaStatusRepository.findById(id);
    }

    @Override
    public Optional<Pro_Forma_Status> createProFormaStatus(String name, String description) {
        return this.proFormaStatusRepository.create(name, description);
    }

    @Override
    public Optional<Pro_Forma_Status> updateProFormaStatus(Short id, String name, String description) {
        return this.proFormaStatusRepository.edit(id, name, description);
    }

    @Override
    public List<Pro_Forma_Status> getProFormaStatusByName(String name) {
        return this.proFormaStatusRepository.findAllByName(name);
    }

    @Override
    public void delete(Short id) {
        this.proFormaStatusRepository.deleteById(id);
    }
}
