package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from article"
    )
    List<Article> listAll();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from article a " +
                    "where a.art_name like ?1"
    )
    List<Article> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from article a " +
                    "where a.art_id=?1"
    )
    Optional<Article> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into article(art_name, art_image, art_weight, ctg_id, man_id) " +
                    "values (?1,?2,?3,?4,?5)"
    )
    Optional<Article> create(
            @NonNull @Param("name") String name,
            @NonNull @Param("image") String image,
            @NonNull @Param("weight") Integer weight,
            @NonNull @Param("ctg") Long ctg_id,
            @NonNull @Param("man") Long man_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update article " +
                    "set art_name=?2,art_image=?3,art_weight=?4,ctg_id=?5,man_id=?6 " +
                    "where art_id=?1"
    )
    Optional<Article> edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("name") String name,
            @NonNull @Param("img") String image,
            @NonNull @Param("weight") Integer weight,
            @NonNull @Param("ctg") Long ctg_id,
            @NonNull @Param("man") Long man_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from article where art_id=?1"
    )
    void delete(@Param("id") Long id);
}
