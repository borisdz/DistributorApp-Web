package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.CategoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select ctg_id as id,
                           ctg_name as name
                    from category
                    """
    )
    List<CategoryDto> listAll();

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
    Integer edit(@NonNull Integer id,
                 @NonNull String name);

    @Modifying
    @Transactional
    @Query(
            value = "delete from category " +
                    "where ctg_id = ?1",
            nativeQuery = true)
    void deleteById(@NonNull Integer id);
}