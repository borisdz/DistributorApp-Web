package mk.ukim.finki.db.distributorapp.discount;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = """
                    insert into discount(dsc_amount)
                    values (:discountAmount)
                    """)
    Integer create(
            @NotNull @Param("discountAmount") Double discountAmount);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    update discount
                    set dsc_amount=:discountAmount
                    where dsc_id=:id
                    """)
    Integer edit(
            @NotNull @Param("id") Long id,
            @NotNull @Param("discountAmount") Double discountAmount
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    delete from discount where dsc_id=:dsc_id
                    """
    )
    void delete(@NonNull @Param("dsc_id") Long id);
}
