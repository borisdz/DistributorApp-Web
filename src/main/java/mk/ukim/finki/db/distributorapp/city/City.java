package mk.ukim.finki.db.distributorapp.city;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.region.Region;
import mk.ukim.finki.db.distributorapp.users.User;
import mk.ukim.finki.db.distributorapp.warehouse.Warehouse;

import java.util.List;

@Entity
@Data
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Size(max = 255)
    @NotNull
    @Column(name = "city_name", nullable = false)
    private String cityName;

    @OneToMany(mappedBy = "city")
    private List<User> users;

    @OneToOne(mappedBy = "city")
    private Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

}
