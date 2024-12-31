package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from manufacturer"
    )
    List<Manufacturer> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from manufacturer where man_name like :name"
    )
    List<Manufacturer> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * from manufacturer where man_id=:id"
    )
    Optional<Manufacturer> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into manufacturer(man_name, man_address, man_mobile, man_email) " +
                    "values (:name,:adr,:mob,:email)"
    )
    Optional<Manufacturer> create(
            @NonNull @Param("name") String name,
            @NonNull @Param("adr") String address,
            @NonNull @Param("mob") String mobile,
            @NonNull @Param("email") String email);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update manufacturer " +
                    "set man_name=:name,man_address=:adr,man_mobile=:mob,man_email=:email " +
                    "where man_id=:id"
    )
    Optional<Manufacturer> edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("name") String name,
            @NonNull @Param("adr") String address,
            @NonNull @Param("mob") String mobile,
            @NonNull @Param("email") String email);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from manufacturer where man_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);
}
