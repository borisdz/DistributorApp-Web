package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from delivery"
    )
    List<Delivery> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from delivery " +
                    "where veh_id = ?1"
    )
    List<Delivery> findAllByVehicle(@NonNull Integer veh_id);

    @Query(
            nativeQuery = true,
            value = "select * from delivery where del_id = ?1"
    )
    Optional<Delivery> findById(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = "select d.* " +
                    "from delivery d join vehicle v on d.veh_id = v.veh_id " +
                    "join driver dr on v.veh_id = dr.veh_id " +
                    "where dr.user_id = ?1"
    )
    List<Delivery> findDeliveriesByDriver(@NonNull Long driver_id);

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
            @NonNull Integer del_start_km,
            @NonNull Integer del_end_km,
            @NonNull LocalTime del_start_time,
            @NonNull LocalTime del_end_time,
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
                    select de.*
                    from driver d join vehicle v on d.veh_id = v.veh_id
                    join delivery de on v.veh_id = de.veh_id
                    where d.user_id=:driver and de.d_status_id =1
                    order by de.del_date desc;
                    """
    )
    List<Delivery> getNewDeliveriesByDriver(@NonNull @Param("driver") Long driver_id);

    @Query(
            nativeQuery = true,
            value = """
                    select del.del_id as id,
                           del.del_date_created as dateCreated,
                           del.del_date as deliveryDate,
                           del.del_start_km as delStartKm,
                           del.del_end_km as delEndKm,
                           del.del_start_time as delStartTime,
                           del.del_end_time as delEndTime,
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
                           d.del_start_time as delStartTime,
                           d.del_end_time as delEndTime,
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
                    where m.user_id=:manager
                    """
    )
    List<DeliveryDto> getCurrentDeliveriesByManager(@NonNull @Param("manager") Long manager_id);
}
