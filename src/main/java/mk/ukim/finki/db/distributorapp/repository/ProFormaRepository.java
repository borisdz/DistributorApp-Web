package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Pro_Forma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProFormaRepository extends JpaRepository<Pro_Forma,Long> {
    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Pro_Forma> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Pro_Forma> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Pro_Forma> create(LocalDate pf_deadline, LocalDate pf_create_date, Short pf_status_id, Long order_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Pro_Forma> edit(Long pf_id, LocalDate pf_deadline, LocalDate pf_create_date, Short pf_status_id, Long order_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();
}
