package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wh_id")
    private Integer warehouse_id;

    @Column(name = "wh_address", nullable = false)
    private String warehouse_address;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToOne(mappedBy = "warehouse")
    private Manager manager;

    @OneToMany(mappedBy = "warehouse")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "warehouse")
    private List<Article_Unit> articleUnits;
}
