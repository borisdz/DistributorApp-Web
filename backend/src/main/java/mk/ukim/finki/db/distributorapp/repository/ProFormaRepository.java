package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.ProForma;
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
            value = "select * from pro_forma"
    )
    List<ProForma> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from pro_forma where pf_id=:id"
    )
    Optional<ProForma> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into pro_forma(pf_deadline, pf_date_created, pf_status_id) " +
                    "values (:ddl,:createD,:status)"
    )
    Optional<ProForma> create(
            @NonNull @Param("ddl") LocalDate pf_deadline,
            @NonNull @Param("createD") LocalDate pf_create_date,
            @NonNull @Param("status") Short pf_status_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update pro_forma " +
                    "set pf_deadline=:ddl,pf_date_created=:createD,pf_status_id=:status " +
                    "where pf_id=:id"
    )
    Optional<ProForma> edit(
            @NonNull @Param("id") Long pf_id,
            @NonNull @Param("ddl") LocalDate pf_deadline,
            @NonNull @Param("createD") LocalDate pf_create_date,
            @NonNull @Param("status") Short pf_status_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from pro_forma where pf_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);
}
