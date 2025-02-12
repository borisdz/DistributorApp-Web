package mk.ukim.finki.db.distributorapp.vehicle;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleBasicDto;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
            value = """
                    select veh_id as id,
                           wh_id as warehouseId,
                           veh_plate as plateNumber
                    from vehicle
                    where wh_id = ?1
                    """
    )
    List<VehicleBasicDto> findAllByWarehouseDto(@NonNull Integer warehouseId);

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
            @NonNull Date vehicleLastService,
            @NonNull Integer vehicleLastServiceKm,
            @NonNull String vehiclePlate,
            @NonNull String vehicleVIN,
            @NonNull Date vehicleRegDate,
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
            @NonNull Date vehicleLastService,
            @NonNull Integer vehicleLastServiceKm,
            @NonNull String vehiclePlate,
            @NonNull String vehicleVIN,
            @NonNull Date vehicleRegDate,
            @NonNull Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from vehicle where veh_id=?1"
    )
    void delete(@NonNull Integer id);

    //----------------------------------------------------------------------------------------------------------------------
    @Query(
            nativeQuery = true,
            value = """
                    select v.veh_id as id,
                           v.veh_carry_weight as carryWeight,
                           v.veh_service_interval as serviceInterval,
                           v.veh_kilometers as kilometers,
                           v.veh_last_service as lastServiceDate,
                           v.veh_last_service_km as lastServiceKm,
                           v.veh_plate as plate,
                           v.veh_vin as vin,
                           v.veh_reg as registrationDate,
                           w.wh_id as whId,
                           c.city_name as city,
                           r.region_name as region,
                           d.user_id as driverId,
                           u1.user_name as driverName,
                           u1.user_email as driverEmail,
                           u1.user_mobile as driverPhone,
                           u1.user_image as driverImg
                    from warehouse w
                    join city c on w.city_id = c.city_id
                    join region r on c.region_id = r.region_id
                    join manager m on w.wh_id = m.wh_id
                    join users u on m.user_id = u.user_id
                    join vehicle v on w.wh_id=v.wh_id
                    join driver d on d.veh_id=v.veh_id
                    join users u1 on d.user_id=u1.user_id
                    where m.user_id = :manager
                    group by v.veh_id, v.veh_carry_weight, v.veh_service_interval, v.veh_kilometers, v.veh_last_service, v.veh_last_service_km, v.veh_plate, v.veh_vin, v.veh_reg, w.wh_id, c.city_name, r.region_name, d.user_id, u1.user_name, u1.user_email, u1.user_mobile, u1.user_image
                    order by v.veh_reg
                   """
    )
    List<VehicleDto> getVehiclesByManager(@NonNull @Param("manager") Long managerId);
}
