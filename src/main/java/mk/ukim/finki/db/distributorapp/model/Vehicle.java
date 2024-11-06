package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private Double carry_weight;

    @OneToMany(mappedBy = "vehicle",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveries;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;
}
