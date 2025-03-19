package mk.ukim.finki.db.distributorapp.region;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.city.City;

import java.util.List;

@Entity
@Data
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id", nullable = false)
    private Integer regionId;

    @Size(max = 255)
    @NotNull
    @Column(name = "region_name", nullable = false)
    private String regionName;

    @OneToMany(mappedBy = "region")
    private List<City> cities;

}
