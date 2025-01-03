package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(
            value = "select * " +
                    "from category c " +
                    "where c.ctg_name like :name",
            nativeQuery = true)
    List<Category> findAllByName(@NonNull @Param("name") String name);

    @Query(
            value = "select * " +
                    "from category",
            nativeQuery = true)
    List<Category> listAll();

    @Query(
            value = "select * " +
                    "from category c " +
                    "where c.ctg_id=:id",
            nativeQuery = true
    )
    Optional<Category> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            value = "insert into category(ctg_name) " +
                    "values (:name)",
            nativeQuery = true
    )
    Integer create(@NonNull @Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update category " +
                    "set ctg_name=:name " +
                    "where ctg_id=:id"
    )
    Integer edit(@NonNull @Param("id") Long id,
                 @NonNull @Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            value = "delete from category " +
                    "where ctg_id = :id",
            nativeQuery = true)
    void deleteById(@NonNull @Param("id") Long id);
}