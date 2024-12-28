package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query(
//            value = "select * " +
//                    "from category c " +
//                    "where c.ctg_name like :name",
//            nativeQuery = true)
//    List<Category> findAllByName(@NonNull @Param("name") String name);

    @Query(
            value = "select * " +
                    "from category c " +
                    "where c.ctg_name like ?1",
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
                    "where c.ctg_id = ?1",
            nativeQuery = true
    )
    Optional<Category> findById(@NonNull @Param("ctg_id") Long id);

    @Modifying
    @Transactional
    @Query(
            value = "insert into category(ctg_name,ctg_desc) " +
                    "values (:name, :desc)",
            nativeQuery = true
    )
    Optional<Category> create(@NonNull @Param("name") String name,
                              @NonNull @Param("desc") String desc);

    @Modifying
    @Transactional
    @Query(
            value = "update category c " +
                    "set c.ctg_name = :name, c.ctg_desc = :desc " +
                    "where c.ctg_id = :id",
            nativeQuery = true
    )
    Optional<Category> edit(@Param("id") Long id,
                            @Param("name") String name,
                            @Param("desc") String description);

    @Modifying
    @Transactional
    @Query(
            value = "delete from category " +
                    "where ctg_id = :id",
            nativeQuery = true)
    void deleteById(@NonNull @Param("id") Long id);
}