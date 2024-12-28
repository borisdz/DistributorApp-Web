package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "vehicle", schema = "IND0_185022")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veh_id")
    private Integer vehicleId;

    @Column(name = "veh_carry_weight", nullable = false)
    private Integer vehicleCarryWeight;

    @Column(name = "veh_service_interval", nullable = false)
    private Short vehicleServiceInterval;

    @Column(name = "veh_kilometers", nullable = false)
    private Integer vehicleKilometers;

    @Column(name = "veh_last_service")
    private LocalDate vehicleLastService;

    @Column(name = "veh_last_service_km")
    private Integer vehicleLastServiceKm;

    @Column(name = "veh_plate", nullable = false, length = 8)
    private String vehiclePlate;

    @Column(name = "veh_vin", nullable = false, length = 17)
    private String vehicleVin;

    @Column(name = "veh_reg", nullable = false)
    private LocalDate vehicleRegDate;

    @ManyToOne
    @JoinColumn(name = "wh_id", nullable = false)
    private Warehouse warehouse;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

    @OneToMany(mappedBy = "vehicle")
    private List<Delivery> deliveries;
}
