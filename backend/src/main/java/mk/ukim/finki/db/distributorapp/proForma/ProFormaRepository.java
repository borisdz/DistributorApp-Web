package mk.ukim.finki.db.distributorapp.proForma;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;
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
            value = """
                    select pf.pf_id as id,
                           pf.pf_deadline as pfDeadline,
                           pf.pf_date_created as pfDateCreated,
                           pf.pf_status_id as statusId,
                           pfs.pf_status_name as statusName,
                           o.ord_id as ordId,
                           o.cust_id as customerId,
                           c.cust_company_name as customerName,
                           u.user_email as customerEmail,
                           u.user_mobile as customerPhone
                    from pro_forma pf
                    join pro_forma_status pfs on pf.pf_status_id = pfs.pf_status_id
                    join orders o on pf.pf_id = o.pf_id
                    join customer c on o.cust_id = c.user_id
                    join users u on c.user_id = u.user_id
                    """
    )
    List<ProFormaDto> listAll();

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
