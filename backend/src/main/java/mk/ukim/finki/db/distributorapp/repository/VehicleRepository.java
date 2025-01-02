package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
            value = "select * from vehicle where wh_id=:wh"
    )
    List<Vehicle> findAllByWarehouse(@NonNull @Param("wh") Integer warehouseId);

    @Query(
            nativeQuery = true,
            value = "select * from vehicle where veh_id=:id"
    )
    Optional<Vehicle> findById(@NonNull @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into vehicle(veh_carry_weight, veh_service_interval, veh_kilometers, veh_last_service, veh_last_service_km, veh_plate, veh_vin, veh_reg, wh_id) " +
                    "values (:carryW,:serInterval,:vehKm,:lastSerD,:lastSerKm,:plate,:vin,:vehRegD,:wh)"
    )
    Optional<Vehicle> create(
            @NonNull @Param("carryW") Integer vehicleCarryWeight,
            @NonNull @Param("serInterval") Short vehicleServiceInterval,
            @NonNull @Param("vehKm") Integer vehicleKilometers,
            @NonNull @Param("lastSerD") LocalDate vehicleLastService,
            @NonNull @Param("lastSerKm") Integer vehicleLastServiceKm,
            @NonNull @Param("plate") String vehiclePlate,
            @NonNull @Param("vin") String vehicleVIN,
            @NonNull @Param("vehRegD") LocalDate vehicleRegDate,
            @NonNull @Param("wh") Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update vehicle " +
                    "set veh_carry_weight=:carryW,veh_service_interval=:serInterval,veh_kilometers=:vehKm," +
                    "veh_last_service=:lastSerD,veh_last_service_km=:lastSerKm,veh_plate=:plate,veh_vin=:vin,veh_reg=:vehRegD,wh_id=:wh " +
                    "where veh_id=:id"
    )
    Optional<Vehicle> edit(
            @NonNull @Param("id") Integer id,
            @NonNull @Param("carryW") Integer vehicleCarryWeight,
            @NonNull @Param("serInterval") Short vehicleServiceInterval,
            @NonNull @Param("vehKm") Integer vehicleKilometers,
            @NonNull @Param("lastSerD") LocalDate vehicleLastService,
            @NonNull @Param("lastSerKm") Integer vehicleLastServiceKm,
            @NonNull @Param("plate") String vehiclePlate,
            @NonNull @Param("vin") String vehicleVIN,
            @NonNull @Param("vehRegD") LocalDate vehicleRegDate,
            @NonNull @Param("wh") Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from vehicle where veh_id=:id"
    )
    void delete(@NonNull @Param("id") Integer id);
}
