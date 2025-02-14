package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.model.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into delivery(del_date_created, del_date, del_start_km, del_end_km, del_start_time, del_end_time, d_status_id, veh_id) " +
                    "values (?1,?2,?3,?4,?5,?6,?7,?8)"
    )
    Integer create(
            @NonNull Date del_date_created,
            @NonNull Date del_date,
            Integer del_start_km,
            Integer del_end_km,
            LocalTime del_start_time,
            LocalTime del_end_time,
            @NonNull Short del_status_id,
            @NonNull Integer veh_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update delivery " +
                    "set del_date_created = ?2,del_date = ?3,del_start_km = ?4,del_end_km = ?5,del_start_time = ?6,del_end_time = ?7,d_status_id = ?8,veh_id = ?9 " +
                    "where del_id = ?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull Date del_date_created,
            @NonNull Date del_date,
            Integer del_start_km,
            Integer del_end_km,
            LocalTime del_start_time,
            LocalTime del_end_time,
            @NonNull Short del_status_id,
            @NonNull Integer veh_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from delivery where del_id = ?1"
    )
    void delete(@NonNull Long id);

    //------------------------------------------------------------------------------------------------------------------
    @Query(
            nativeQuery = true,
            value = """
                    select del.del_id as id,
                           del.del_date_created as dateCreated,
                           del.del_date as deliveryDate,
                           del.del_start_km as delStartKm,
                           del.del_end_km as delEndKm,
                           to_char(d.del_start_time, 'HH24:MI:22') as delStartTime,
                           to_char(d.del_end_time, 'HH24:MI:SS') as delEndTime,
                           del.d_status_id as dStatusId,
                           ds.d_status_name as delStatus,
                           v.veh_id as vehId,
                           dr.user_id as driverId,
                           u.user_name as driverName,
                           u.user_image as driverImage
                    from delivery del
                        join orders o on o.del_id=del.del_id
                        join delivery d on o.del_id = d.del_id
                        join delivery_status ds on d.d_status_id=ds.d_status_id
                        join vehicle v on v.veh_id=d.veh_id
                        join driver dr on dr.veh_id=v.veh_id
                        join users u on u.user_id=dr.user_id
                    where o.cust_id=:customer and del.d_status_id <> 4;
                    """
    )
    List<DeliveryDto> getCurrentDeliveriesByCustomer(@NonNull @Param("customer") Long customer_id);

    @Query(
            nativeQuery = true,
            value = """
                    select d.del_id as id,
                           d.del_date_created as dateCreated,
                           d.del_date as deliveryDate,
                           d.del_start_km as delStartKm,
                           d.del_end_km as delEndKm,
                           to_char(d.del_start_time, 'HH24:MI:22') as delStartTime,
                           to_char(d.del_end_time, 'HH24:MI:SS') as delEndTime,
                           d.d_status_id as dStatusId,
                           ds.d_status_name as delStatus,
                           v.veh_id as vehId,
                           dr.user_id as driverId,
                           u.user_name as driverName,
                           u.user_image as driverImage
                    from warehouse w
                        join manager m on w.wh_id = m.wh_id
                        join article_unit au on w.wh_id = au.wh_id
                        join orders o on au.ord_id = o.ord_id
                        join delivery d on o.del_id = d.del_id
                        join delivery_status ds on d.d_status_id=ds.d_status_id
                        join vehicle v on v.veh_id=d.veh_id
                        join driver dr on dr.veh_id=v.veh_id
                        join users u on u.user_id=dr.user_id
                    where m.user_id=:manager and d.d_status_id<>4
                    """
    )
    List<DeliveryDto> getCurrentDeliveriesByManager(@NonNull @Param("manager") Long manager_id);

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
                    join delivery_status ds on ds.d_status_id=del.d_status_id
                    join vehicle v on del.veh_id = v.veh_id
                    join driver d on d.veh_id = v.veh_id
                    join users u on u.user_id=d.user_id
                    where v.veh_id = ?1
                    """
    )
    List<DeliverySimpleDto> getDeliveriesByVehicle(Integer vehicleId);

    @Query(
            nativeQuery = true,
            value = """
                    select d.del_id as delId,
                           d.del_date_created as delDateCreated,
                           d.del_date as delDate,
                           d.del_start_km as delStartKm,
                           d.del_end_km as delEndKm,
                           to_char(d.del_start_time, 'HH24:MI:22') as delStartTime,
                           to_char(d.del_end_time, 'HH24:MI:SS') as delEndTime,
                           d.d_status_id as delStatusId,
                           d.veh_id as veh_id
                    from delivery d
                    where d.del_id = ?1
                    """
    )
    DeliveryFullDto findDeliveryDtoById(@Param("id") Long id);
}
