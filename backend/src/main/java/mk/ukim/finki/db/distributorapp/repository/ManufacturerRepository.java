package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.ManufacturerDto;
import mk.ukim.finki.db.distributorapp.model.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    @Query(
            nativeQuery = true,
            value = """
                    select m.man_id as id,
                           m.man_name as name,
                           m.man_adr as address,
                           m.man_mobile as phone,
                           m.man_email as email
                    from manufacturer m
                    """
    )
    List<ManufacturerDto> listAll();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    insert into manufacturer(man_name, man_adr, man_mobile, man_email)
                    values (?1,?2,?3,?4)
                    """
    )
    Integer create(
            @NonNull String name,
            @NonNull String address,
            @NonNull String mobile,
            @NonNull String email);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    update manufacturer
                    set man_name=?2,man_adr=?3,man_mobile=?4,man_email=?5
                    where man_id=?1
                    """
    )
    Integer edit(
            @NonNull Long id,
            @NonNull String name,
            @NonNull String address,
            @NonNull String mobile,
            @NonNull String email);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from manufacturer where man_id=?1"
    )
    void delete(@NonNull Long id);
}
