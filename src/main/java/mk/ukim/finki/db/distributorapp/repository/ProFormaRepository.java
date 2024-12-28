package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.ProForma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProFormaRepository extends JpaRepository<ProForma, Long> {
    @Query(
            nativeQuery = true,
            value = ""
    )
    List<ProForma> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<ProForma> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<ProForma> create(LocalDate pf_deadline, LocalDate pf_create_date, Short pf_status_id, Long order_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<ProForma> edit(Long pf_id, LocalDate pf_deadline, LocalDate pf_create_date, Short pf_status_id, Long order_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();
}
