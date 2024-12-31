package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("vehicleId")
    private Integer vehicleId;

    @Column(name = "veh_carry_weight", nullable = false)
    @JsonProperty("vehicleCarryWeight")
    private Integer vehicleCarryWeight;

    @Column(name = "veh_service_interval", nullable = false)
    @JsonProperty("vehicleServiceInterval")
    private Short vehicleServiceInterval;

    @Column(name = "veh_kilometers", nullable = false)
    @JsonProperty("vehicleKilometers")
    private Integer vehicleKilometers;

    @Column(name = "veh_last_service")
    @JsonProperty("vehicleLastService")
    private LocalDate vehicleLastService;

    @Column(name = "veh_last_service_km")
    @JsonProperty("vehicleLastServiceKm")
    private Integer vehicleLastServiceKm;

    @Column(name = "veh_plate", nullable = false, length = 8)
    @JsonProperty("vehiclePlate")
    private String vehiclePlate;

    @Column(name = "veh_vin", nullable = false, length = 17)
    @JsonProperty("vehicleVin")
    private String vehicleVin;

    @Column(name = "veh_reg", nullable = false)
    @JsonProperty("vehicleRegDate")
    private LocalDate vehicleRegDate;

    @ManyToOne
    @JoinColumn(name = "wh_id", nullable = false)
    private Warehouse warehouse;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

    @OneToMany(mappedBy = "vehicle")
    private List<Delivery> deliveries;
}
