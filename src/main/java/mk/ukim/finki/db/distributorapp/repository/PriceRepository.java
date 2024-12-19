package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Price> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Price> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Price> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Price> create();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Price> edit();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();
}
