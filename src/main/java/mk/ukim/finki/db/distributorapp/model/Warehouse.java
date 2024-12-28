package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "warehouse", schema = "IND0_185022")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wh_id")
    private Integer warehouseId;

    @Column(name = "wh_address", nullable = false)
    private String warehouseAddress;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToOne(mappedBy = "warehouse")
    private Manager manager;

    @OneToMany(mappedBy = "warehouse")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "warehouse")
    private List<ArticleUnit> articleUnits;
}
