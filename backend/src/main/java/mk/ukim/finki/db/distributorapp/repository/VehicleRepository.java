package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Vehicle;
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
            value = ""
    )
    List<Vehicle> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Vehicle> findAllByWarehouse(@NonNull @Param("wh") Integer warehouseId);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Vehicle> findById(@NonNull @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Vehicle> create(Integer vehicleCarryWeight, Short vehicleServiceInterval, Integer vehicleKilometers,
                             LocalDate vehicleLastService, Integer vehicleLastServiceKm, String vehiclePlate,
                             String vehicleVIN, LocalDate vehicleRegDate, Integer whId, Long driverId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Vehicle> edit(Integer id, Integer vehicleCarryWeight, Short vehicleServiceInterval, Integer vehicleKilometers,
                           LocalDate vehicleLastService, Integer vehicleLastServiceKm, String vehiclePlate,
                           String vehicleVIN, LocalDate vehicleRegDate, Integer whId, Long driverId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete(Integer id);
}
