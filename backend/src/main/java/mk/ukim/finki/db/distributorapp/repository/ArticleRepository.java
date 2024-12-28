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
            value = "set search_path = \"IND0_185022\"; " +
                    "select * " +
                    "from article"
    )
    List<Article> listAll();

    @Query(
            value = "set search_path = \"IND0_185022\"; " +
                    "select * " +
                    "from article a " +
                    "where a.art_name like :name",
            nativeQuery = true
    )
    List<Article> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    "select * " +
                    "from article a " +
                    "where a.art_id=:id"
    )
    Optional<Article> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    Optional<Article> create(String name, Integer weight, Long ctg_id, Long man_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    Optional<Article> edit(Long id, String name, Integer weight, Long ctg_id, Long man_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    void delete(Long id);
}