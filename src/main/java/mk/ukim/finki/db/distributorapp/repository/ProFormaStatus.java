package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.statuses.Pro_Forma_Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProFormaStatus extends JpaRepository<Pro_Forma_Status, Short> {
    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Pro_Forma_Status> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Pro_Forma_Status> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Pro_Forma_Status> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Pro_Forma_Status> create();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Pro_Forma_Status> edit();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();
}
