package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from driver d join users u on d.user_id = u.user_id"
    )
    List<Driver> listAll();

    @Query(
            nativeQuery = true,
            value = "select d.* from driver d join users u on d.user_id = u.user_id " +
                    "where u.user_name like ?1"
    )
    List<Driver> findAllByName(@NonNull String name);

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from driver d join users u on d.user_id = u.user_id
                    where d.user_id=?1
                    """
    )
    Optional<Driver> findById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into driver(user_id, veh_id) " +
                    "values (?1,?2)"
    )
    Integer create(
            @NonNull Long usr_id,
            @NonNull Integer veh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update driver " +
                    "set veh_id=?2 " +
                    "where user_id=?1"
    )
    Integer edit(
            @NonNull Long usr_id,
            @NonNull Integer veh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from driver where user_id=?1"
    )
    void delete(@NonNull Long id);

    //    ---------------Dashboard queries------------------------------------------
    @Query(
            nativeQuery = true,
            value = """
                    select de.del_id as id,
                           de.del_date_created as dateCreated,
                           de.del_date as delDate,
                           de.del_start_km as delStartKm,
                           de.del_end_km as delEndKm,
                           de.del_start_time as delStartTime,
                           de.del_end_time as delEndTime,
                           de.d_status_id as delStatusId,
                           ds.d_status_name as delStatus,
                           v.veh_id as vehId,
                           d.user_id as driverId,
                           u.user_name as driverName,
                           u.user_image as driverImg
                    from driver d
                    join users u on u.user_id = d.user_id
                    join vehicle v on d.veh_id = v.veh_id
                    join delivery de on v.veh_id = de.veh_id
                    join delivery_status ds on de.d_status_id = ds.d_status_id
                    where d.user_id = ?1 and de.d_status_id = 1
                    order by de.del_date desc
                    """
    )
    List<DeliveryDto> activeAssignedDeliveries(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = """
                    select de.del_id as id,
                           de.del_date_created as dateCreated,
                           de.del_date as delDate,
                           de.del_start_km as delStartKm,
                           de.del_end_km as delEndKm,
                           de.del_start_time as delStartTime,
                           de.del_end_time as delEndTime,
                           de.d_status_id as delStatusId,
                           ds.d_status_name as delStatus,
                           v.veh_id as vehId,
                           d.user_id as driverId,
                           u.user_name as driverName,
                           u.user_image as driverImg
                    from driver d
                    join users u on u.user_id = d.user_id
                    join vehicle v on d.veh_id = v.veh_id
                    join delivery de on v.veh_id = de.veh_id
                    join delivery_status ds on de.d_status_id = ds.d_status_id
                    where d.user_id = ?1 and de.d_status_id not between 1 and 3
                    order by de.del_date desc
                    """
    )
    List<DeliveryDto> finishedAssignedDeliveries(@NonNull Long id);
}
