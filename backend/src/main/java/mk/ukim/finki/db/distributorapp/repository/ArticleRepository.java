package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Article;
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
            value = """
                    select a.art_id as id,
                           a.art_name as name,
                           m.man_name as manufacturer,
                           0 as quantity,
                           a.man_id as manufacturerId,
                           p.price as price,
                           c.ctg_name as category,
                           a.ctg_id as categoryId,
                           a.art_weight as weight,
                           a.art_image as image
                    from article a
                    join manufacturer m on a.man_id = m.man_id
                    join price p on p.art_id = a.art_id
                    join category c on a.ctg_id = c.ctg_id
                    """
    )
    List<ArticleDto> listAll();

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
    Integer create(
            @NonNull @Param("name") String name,
            @NonNull @Param("image") String image,
            @NonNull @Param("weight") Integer weight,
            @NonNull @Param("ctg") Integer ctg_id,
            @NonNull @Param("man") Long man_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update article " +
                    "set art_name=?2,art_image=?3,art_weight=?4,ctg_id=?5,man_id=?6 " +
                    "where art_id=?1"
    )
    Integer edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("name") String name,
            @NonNull @Param("img") String image,
            @NonNull @Param("weight") Integer weight,
            @NonNull @Param("ctg") Integer ctg_id,
            @NonNull @Param("man") Long man_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from article where art_id=?1"
    )
    void delete(@Param("id") Long id);


    @Query(
            nativeQuery = true,
            value = """
                    with stock as (
                        select a.art_id,
                               count(au.unit_id) as quantity
                        from article a
                        join price p on a.art_id = p.art_id
                        join unit_price up on p.price_id = up.price_id
                        join article_unit au on up.unit_id = au.unit_id
                        group by a.art_id
                    )
                    select a.art_id as id,
                           a.art_name as name,
                           m.man_name as manufacturer,
                           st.quantity as quantity,
                           m.man_id as manufacturerId,
                           p.price as price,
                           c.ctg_name as category,
                           c.ctg_id as categoryId,
                           a.art_weight as weight,
                           a.art_image as image
                    from article a
                    join stock st on st.art_id=a.art_id
                    join manufacturer m on a.man_id = m.man_id
                    join category c on a.ctg_id = c.ctg_id
                    join price p on a.art_id = p.art_id
                    join unit_price up on p.price_id = up.price_id
                    join article_unit au on up.unit_id = au.unit_id
                    join warehouse w on w.wh_id = au.wh_id
                    where w.wh_id = ?1
                    """
    )
    List<ArticleDto> findAllByWarehouse(Integer warehouseId);
}
