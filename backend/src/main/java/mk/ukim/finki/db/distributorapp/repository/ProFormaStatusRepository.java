package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.statuses.ProFormaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProFormaStatusRepository extends JpaRepository<ProFormaStatus, Short> {
    @Query(
            nativeQuery = true,
            value = ""
    )
    List<ProFormaStatus> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<ProFormaStatus> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<ProFormaStatus> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<ProFormaStatus> create(String name, String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<ProFormaStatus> edit(Short id, String name, String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete(Short id);
}
