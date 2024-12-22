package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.statuses.Pro_Forma_Status;

import java.util.List;
import java.util.Optional;

public interface ProFormaStatusService {
    List<Pro_Forma_Status> listProFormaStatus();

    Optional<Pro_Forma_Status> getProFormaStatusById(Short id);

    Optional<Pro_Forma_Status> createProFormaStatus(String name, String description);

    Optional<Pro_Forma_Status> updateProFormaStatus(Short id, String name, String description);

    List<Pro_Forma_Status> getProFormaStatusByName(String name);

    void delete(Short id);
}
