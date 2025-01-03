package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "man_id")
    private Long manufacturerId;

    @Column(name = "man_name", nullable = false)
    private String manufacturerName;

    @Column(name = "man_address", nullable = false)
    private String manufacturerAddress;

    @Column(name = "man_mobile", nullable = false)
    private String manufacturerMobile;

    @Column(name = "man_email", nullable = false)
    private String manufacturerEmail;

    @OneToMany(mappedBy = "manufacturer")
    private List<Article> articles;
}
