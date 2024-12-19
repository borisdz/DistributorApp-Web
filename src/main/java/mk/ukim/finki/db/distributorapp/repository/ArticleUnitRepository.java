package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Article_Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleUnitRepository extends JpaRepository<Article_Unit,Long> {
    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Article_Unit> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Article_Unit> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Article_Unit> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Article_Unit> create();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Article_Unit> edit();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();
}
