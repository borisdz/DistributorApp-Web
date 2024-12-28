package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.statuses.ProFormaStatus;

import java.util.List;
import java.util.Optional;

public interface ProFormaStatusService {
    List<ProFormaStatus> listProFormaStatus();

    Optional<ProFormaStatus> getProFormaStatusById(Short id);

    Optional<ProFormaStatus> createProFormaStatus(String name, String description);

    Optional<ProFormaStatus> updateProFormaStatus(Short id, String name, String description);

    List<ProFormaStatus> getProFormaStatusByName(String name);

    void delete(Short id);
}
