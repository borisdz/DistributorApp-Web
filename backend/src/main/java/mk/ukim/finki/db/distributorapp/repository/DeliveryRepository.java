package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
                    "where veh_id=?1"
    )
    List<Delivery> findAllByVehicle(@NonNull Integer veh_id);

    @Query(
            nativeQuery = true,
            value = "select * from delivery where del_id=?1"
    )
    Optional<Delivery> findById(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = "select d.* " +
                    "from delivery d join vehicle v on d.veh_id = v.veh_id " +
                    "join driver dr on v.veh_id = dr.veh_id " +
                    "where dr.user_id=?1"
    )
    List<Delivery> findDeliveriesByDriver(@NonNull Long driver_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into delivery(del_date_created, del_date, del_start_km, del_end_km, del_start_time, del_end_time, del_status_id, veh_id) " +
                    "values (?1,?2,?3,?4,?5,?6,?7,?8)"
    )
    Integer create(
            @NonNull LocalDate del_date_created,
            @NonNull LocalDate del_date,
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
            value = "update delivery " +
                    "set del_date_created=?2,del_date=?3,del_start_km=?4,del_end_km=?5,del_start_time=?6,del_end_time=?7,del_status_id=?8,veh_id=?9 " +
                    "where del_id=?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull LocalDate del_date_created,
            @NonNull LocalDate del_date,
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
            value = "delete from delivery where del_id=?1"
    )
    void delete(@NonNull Long id);
}
