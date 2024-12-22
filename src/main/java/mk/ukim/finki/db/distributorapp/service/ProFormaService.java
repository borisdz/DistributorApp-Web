package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.Pro_Forma;
import mk.ukim.finki.db.distributorapp.model.statuses.Pro_Forma_Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProFormaService {
    List<Pro_Forma> getAllPro_Forma();

    Optional<Pro_Forma> findProFormaById(Long id);

    Optional<Pro_Forma> create(LocalDate pf_deadline, LocalDate pf_create_date, Pro_Forma_Status pf_status, Orders order);

    Optional<Pro_Forma> edit(Long id, LocalDate pf_deadline, LocalDate pf_create_date, Pro_Forma_Status pf_status, Orders order);

    void delete(Long id);

}
