package mk.ukim.finki.db.distributorapp.imageStore;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.imageStore.dto.ImageStoreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageStoreRepository extends JpaRepository<ImageStore, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from image_store
                    where img_ent_type like :ent_type and img_ent_id=:ent_id
                    """
    )
    List<ImageStoreDto> findByImgEntTypeAndImgEntId(
            @NonNull @Param(value = "ent_type") String entType,
            @NonNull @Param(value = "ent_id") Long entId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    insert into image_store(img_path,img_ent_type,img_ent_id) 
                    values (:imgPath, :entType, :entId)
                    """
    )
    Integer create(
            @NonNull @Param(value = "imgPath") String imgPath,
            @NonNull @Param(value = "entType") String imgEntType,
            @NonNull @Param(value = "entId") Long imgEntId
    );
}
