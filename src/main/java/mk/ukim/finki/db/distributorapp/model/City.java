package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long city_id;

    @Column(name = "city_name", nullable = false)
    private String city_name;

    @OneToMany(mappedBy = "city")
    private List<Users> users;

    @OneToMany(mappedBy = "city")
    private List<Warehouse> warehouses;
}
