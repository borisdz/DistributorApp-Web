package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wh_id")
    private Integer warehouseId;

    @Column(name = "wh_address", nullable = false)
    private String warehouseAddress;

    @OneToOne
    @JoinColumn(name = "city_id", unique = true, nullable = false)
    private City city;

    @OneToOne(mappedBy = "warehouse")
    private Manager manager;

    @OneToMany(mappedBy = "warehouse")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "warehouse")
    private List<ArticleUnit> articleUnits;
}
