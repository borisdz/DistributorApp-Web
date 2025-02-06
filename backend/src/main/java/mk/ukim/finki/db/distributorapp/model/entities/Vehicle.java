package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veh_id")
    private Integer vehicleId;

    @NotNull
    @Column(name = "veh_carry_weight", nullable = false)
    private Integer vehicleCarryWeight;

    @NotNull
    @Column(name = "veh_service_interval", nullable = false)
    private Short vehicleServiceInterval;

    @NotNull
    @Column(name = "veh_kilometers", nullable = false)
    private Integer vehicleKilometers;

    @Column(name = "veh_last_service")
    private LocalDate vehicleLastService;

    @Column(name = "veh_last_service_km")
    private Integer vehicleLastServiceKm;

    @Size(max = 8)
    @NotNull
    @Column(name = "veh_plate", nullable = false, length = 8)
    private String vehiclePlate;

    @Size(max = 17)
    @NotNull
    @Column(name = "veh_vin", nullable = false, length = 17)
    private String vehicleVin;

    @NotNull
    @Column(name = "veh_reg", nullable = false)
    private LocalDate vehicleRegDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wh_id", nullable = false)
    private Warehouse warehouse;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

    @OneToMany(mappedBy = "vehicle")
    private List<Delivery> deliveries;
}
