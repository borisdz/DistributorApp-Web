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
    private Integer vehicle_id;

    @Column(name = "veh_carry_weight", nullable = false)
    private Integer vehicle_carry_weight;

    @Column(name = "veh_service_interval", nullable = false)
    private Short vehicle_service_interval;

    @Column(name = "veh_kilometers", nullable = false)
    private Integer vehicle_kilometers;

    @Column(name = "veh_last_service")
    private LocalDate vehicle_last_service;

    @Column(name = "veh_last_service_km")
    private Integer vehicle_last_service_km;

    @Column(name = "veh_plate", nullable = false, length = 8)
    private String vehicle_plate;

    @Column(name = "veh_vin", nullable = false, length = 17)
    private String vehicle_vin;

    @Column(name = "veh_reg", nullable = false)
    private LocalDate vehicle_registration_date;

    @ManyToOne
    @JoinColumn(name = "wh_id", nullable = false)
    private Warehouse warehouse;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

    @OneToMany(mappedBy = "vehicle")
    private List<Delivery> deliveries;
}
