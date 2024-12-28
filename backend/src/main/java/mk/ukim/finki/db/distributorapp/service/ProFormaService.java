package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.ProForma;
import mk.ukim.finki.db.distributorapp.model.statuses.Pro_Forma_Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProFormaService {
    List<ProForma> getAllPro_Forma();

    Optional<ProForma> findProFormaById(Long id);

    Optional<ProForma> create(LocalDate pf_deadline, LocalDate pf_create_date, Pro_Forma_Status pf_status, Orders order);

    Optional<ProForma> edit(Long id, LocalDate pf_deadline, LocalDate pf_create_date, Pro_Forma_Status pf_status, Orders order);

    void delete(Long id);

}