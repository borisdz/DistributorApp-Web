package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.model.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
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

    @Query(
            nativeQuery = true,
            value = """
                    select d.user_id as id,
                           u.user_name as name,
                           u.user_email as email,
                           u.user_mobile as phone,
                           u.user_image as image,
                           d.veh_id as vehId
                    from driver d
                    join users u on d.user_id = u.user_id
                    where d.user_id = :id
                    """
    )
    DriverDto findDriverById(@NonNull @Param("id") Long id);

    //    ---------------Dashboard queries------------------------------------------
    @Query(
            nativeQuery = true,
            value = """
                    select del.del_id as deliveryId,
                           u.user_name as driverName,
                           del.del_date as deliveryDate,
                           del.del_date_created as deliveryCreatedDate,
                           del.d_status_id as deliveryStatus,
                           ds.d_status_name as deliveryStatusName
                    from delivery del
                    join delivery_status ds on del.d_status_id = ds.d_status_id
                    join vehicle v on del.veh_id = v.veh_id
                    join driver d on v.veh_id = d.veh_id
                    join users u on u.user_id = d.user_id
                    where d.user_id = ?1 and del.d_status_id = 1
                    """
    )
    List<DeliverySimpleDto> activeAssignedDeliveries(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = """
                    select del.del_id as deliveryId,
                           u.user_name as driverName,
                           del.del_date as deliveryDate,
                           del.del_date_created as deliveryCreatedDate,
                           del.d_status_id as deliveryStatus,
                           ds.d_status_name as deliveryStatusName
                    from delivery del
                    join delivery_status ds on del.d_status_id = ds.d_status_id
                    join vehicle v on del.veh_id = v.veh_id
                    join driver d on v.veh_id = d.veh_id
                    join users u on u.user_id = d.user_id
                    where d.user_id = ?1 and del.d_status_id not between 1 and 3
                    """
    )
    List<DeliverySimpleDto> finishedAssignedDeliveries(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = """
                    select del.del_id as deliveryId,
                           u.user_name as driverName,
                           del.del_date as deliveryDate,
                           del.del_date_created as deliveryCreatedDate,
                           del.d_status_id as deliveryStatus,
                           ds.d_status_name as deliveryStatusName
                    from delivery del
                    join delivery_status ds on del.d_status_id = ds.d_status_id
                    join vehicle v on del.veh_id = v.veh_id
                    join driver d on v.veh_id = d.veh_id
                    join users u on u.user_id = d.user_id
                    where d.user_id = ?1 and del.d_status_id = 3
                    """
    )
    List<DeliverySimpleDto> getOngoingDeliveries(@NonNull Long driverId);
}
