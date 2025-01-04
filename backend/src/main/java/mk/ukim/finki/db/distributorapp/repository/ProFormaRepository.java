package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.ProForma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
            value = "select * from pro_forma where pf_id=?1"
    )
    Optional<ProForma> findById(@NonNull Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into pro_forma(pf_deadline, pf_date_created, pf_status_id) " +
                    "values (?1,?2,?3)"
    )
    Integer create(
            @NonNull LocalDate pf_deadline,
            @NonNull LocalDate pf_create_date,
            @NonNull Short pf_status_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update pro_forma " +
                    "set pf_deadline=?2,pf_date_created=?3,pf_status_id=?4 " +
                    "where pf_id=?1"
    )
    Integer edit(
            @NonNull Long pf_id,
            @NonNull LocalDate pf_deadline,
            @NonNull LocalDate pf_create_date,
            @NonNull Short pf_status_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from pro_forma where pf_id=?1"
    )
    void delete(@NonNull Long id);
}
