package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.ProForma;
import mk.ukim.finki.db.distributorapp.model.ProFormaStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProFormaService {
    List<ProForma> getAllPro_Forma();

    Optional<ProForma> findProFormaById(Long id);

    Optional<ProForma> create(LocalDate pf_deadline, LocalDate pf_create_date, ProFormaStatus pf_status);

    Optional<ProForma> edit(Long id, LocalDate pf_deadline, LocalDate pf_create_date, ProFormaStatus pf_status);

    void delete(Long id);

}
