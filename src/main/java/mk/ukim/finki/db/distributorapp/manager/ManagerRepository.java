package mk.ukim.finki.db.distributorapp.manager;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.manager.dto.ManagerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into manager(user_id, wh_id) " +
                    "values (?1,?2)"
    )
    Integer create(
            @NonNull Long id,
            @NonNull Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update manager " +
                    "set wh_id=?2 " +
                    "where user_id=?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from manager where user_id=?1"
    )
    void delete(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = """
                    select m.user_id as id,
                        u.user_name as name,
                        u.user_email as email,
                        u.user_mobile as phone,
                        u.user_image as image,
                        m.wh_id as whId,
                        r.region_name as whRegion,
                        c.city_name as whCity
                    from manager m
                        join users u on u.user_id=m.user_id
                        join warehouse wh on m.wh_id=wh.wh_id
                        join city c on wh.city_id=c.city_id
                        join region r on c.region_id=r.region_id
                    where m.user_id = :managerId
                    """
    )
    ManagerDto getMangerDtoById(@Param("managerId") Long id);
}
