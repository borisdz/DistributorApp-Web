package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(
            value = "select * " +
                    "from category c " +
                    "where c.ctg_name like ?1",
            nativeQuery = true)
    List<Category> findAllByName(@NonNull String name);

    @Query(
            value = "select * " +
                    "from category",
            nativeQuery = true)
    List<Category> listAll();

    @Query(
            value = "select * " +
                    "from category c " +
                    "where c.ctg_id=?1",
            nativeQuery = true
    )
    Optional<Category> findById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            value = "insert into category(ctg_name) " +
                    "values (?1)",
            nativeQuery = true
    )
    Integer create(@NonNull String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update category " +
                    "set ctg_name=?2 " +
                    "where ctg_id=?1"
    )
    Integer edit(@NonNull Long id,
                 @NonNull String name);

    @Modifying
    @Transactional
    @Query(
            value = "delete from category " +
                    "where ctg_id = ?1",
            nativeQuery = true)
    void deleteById(@NonNull Long id);
}