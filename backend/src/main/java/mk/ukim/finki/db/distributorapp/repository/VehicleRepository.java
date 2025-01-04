package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from vehicle"
    )
    List<Vehicle> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from vehicle where wh_id=?1"
    )
    List<Vehicle> findAllByWarehouse(@NonNull Integer warehouseId);

    @Query(
            nativeQuery = true,
            value = "select * from vehicle where veh_id=?1"
    )
    Optional<Vehicle> findById(@NonNull Integer id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into vehicle(veh_carry_weight, veh_service_interval, veh_kilometers, veh_last_service, veh_last_service_km, veh_plate, veh_vin, veh_reg, wh_id) " +
                    "values (?1,?2,?3,?4,?5,?6,?7,?8,?9)"
    )
    Integer create(
            @NonNull Integer vehicleCarryWeight,
            @NonNull Short vehicleServiceInterval,
            @NonNull Integer vehicleKilometers,
            @NonNull LocalDate vehicleLastService,
            @NonNull Integer vehicleLastServiceKm,
            @NonNull String vehiclePlate,
            @NonNull String vehicleVIN,
            @NonNull LocalDate vehicleRegDate,
            @NonNull Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update vehicle " +
                    "set veh_carry_weight=?2,veh_service_interval=?3,veh_kilometers=?4," +
                    "veh_last_service=?5,veh_last_service_km=?6,veh_plate=?7,veh_vin=?8,veh_reg=?9,wh_id=?10 " +
                    "where veh_id=?1"
    )
    Integer edit(
            @NonNull Integer id,
            @NonNull Integer vehicleCarryWeight,
            @NonNull Short vehicleServiceInterval,
            @NonNull Integer vehicleKilometers,
            @NonNull LocalDate vehicleLastService,
            @NonNull Integer vehicleLastServiceKm,
            @NonNull String vehiclePlate,
            @NonNull String vehicleVIN,
            @NonNull LocalDate vehicleRegDate,
            @NonNull Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from vehicle where veh_id=?1"
    )
    void delete(@NonNull Integer id);
}
