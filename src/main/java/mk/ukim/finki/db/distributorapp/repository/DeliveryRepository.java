package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Delivery;
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
            value = ""
    )
    List<Delivery> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Delivery> findAllByVehicle(@NonNull @Param("vehicle") Integer veh_id);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Delivery> findById(@NonNull @Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Delivery> findDeliveriesByDriver(@NonNull @Param("driver") Long driver_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Delivery> create(
            LocalDate del_date_created,
            LocalDate del_date,
            Integer del_start_km,
            Integer del_end_km,
            LocalTime del_start_time,
            LocalTime del_end_time,
            Short del_status_id,
            Integer veh_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Delivery> edit(
            Long id,
            LocalDate del_date_created,
            LocalDate del_date,
            Integer del_start_km,
            Integer del_end_km,
            LocalTime del_start_time,
            LocalTime del_end_time,
            Short del_status_id,
            Integer veh_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete(Long id);
}
