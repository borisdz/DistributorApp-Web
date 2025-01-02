package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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
                    "where veh_id=:veh"
    )
    List<Delivery> findAllByVehicle(@NonNull @Param("veh") Integer veh_id);

    @Query(
            nativeQuery = true,
            value = "select * from delivery where del_id=:id"
    )
    Optional<Delivery> findById(@NonNull @Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = "select d.* " +
                    "from delivery d join vehicle v on d.veh_id = v.veh_id " +
                    "join driver dr on v.veh_id = dr.veh_id " +
                    "where dr.user_id=:driver"
    )
    List<Delivery> findDeliveriesByDriver(@NonNull @Param("driver") Long driver_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into delivery(del_date_created, del_date, del_start_km, del_end_km, del_start_time, del_end_time, del_status_id, veh_id) " +
                    "values (:dCreated,:dDel,:startKm,:endKm,:startT,:endT,:status,:veh)"
    )
    Optional<Delivery> create(
            @NonNull @Param("dCreated") LocalDate del_date_created,
            @NonNull @Param("dDel") LocalDate del_date,
            @NonNull @Param("startKm") Integer del_start_km,
            @NonNull @Param("endKm") Integer del_end_km,
            @NonNull @Param("startT") LocalTime del_start_time,
            @NonNull @Param("endT") LocalTime del_end_time,
            @NonNull @Param("status") Short del_status_id,
            @NonNull @Param("veh") Integer veh_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update delivery " +
                    "set del_date_created=:dCreated,del_date=:dDel,del_start_km=:startKm,del_end_km=:endKm,del_start_time=:startTime,del_end_time=:endT,del_status_id=:status,veh_id=:veh " +
                    "where del_id=:id"
    )
    Optional<Delivery> edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("dCreated") LocalDate del_date_created,
            @NonNull @Param("dDel") LocalDate del_date,
            @NonNull @Param("startKm") Integer del_start_km,
            @NonNull @Param("endKm") Integer del_end_km,
            @NonNull @Param("startT") LocalTime del_start_time,
            @NonNull @Param("endT") LocalTime del_end_time,
            @NonNull @Param("status") Short del_status_id,
            @NonNull @Param("veh") Integer veh_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from delivery where del_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);
}
