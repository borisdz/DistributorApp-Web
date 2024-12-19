package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "man_id")
    private Long manufacturer_id;

    @Column(name = "man_name", nullable = false)
    private String manufacturer_name;

    @Column(name = "man_address", nullable = false)
    private String manufacturer_address;

    @Column(name = "man_mobile", nullable = false)
    private String manufacturer_mobile;

    @Column(name = "man_email", nullable = false)
    private String manufacturer_email;

    @OneToMany(mappedBy = "manufacturer")
    private List<Article> articles;
}
