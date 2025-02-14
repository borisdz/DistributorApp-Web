package mk.ukim.finki.db.distributorapp.warehouse;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.articleUnit.ArticleUnit;
import mk.ukim.finki.db.distributorapp.city.City;
import mk.ukim.finki.db.distributorapp.manager.Manager;
import mk.ukim.finki.db.distributorapp.vehicle.Vehicle;

import java.util.List;

@Entity
@Data
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wh_id")
    private Integer warehouseId;

    @Size(max = 255)
    @NotNull
    @Column(name = "wh_adr", nullable = false)
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
