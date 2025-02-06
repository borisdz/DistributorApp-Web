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
            nativeQuery = true,
            value = """
                    select *
                    from category c
                    where c.ctg_name like :name
                    """)
    List<Category> findAllByName(@Param("name") @NonNull String name);

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from category
                    """
    )
    List<Category> listAll();

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from category c
                    where c.ctg_id=:id
                    """
    )
    Optional<Category> findById(@Param("id") @NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    insert into category(ctg_name)
                    values (:name)
                    """
    )
    Integer create(@Param("name") @NonNull String name);

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