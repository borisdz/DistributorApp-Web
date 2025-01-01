package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("warehouseId")
    private Integer warehouseId;

    @Column(name = "wh_address", nullable = false)
    @JsonProperty("warehouseAddress")
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
