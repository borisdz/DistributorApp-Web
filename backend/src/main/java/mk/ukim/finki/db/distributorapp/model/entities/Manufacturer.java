package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 255)
    @NotNull
    @Column(name = "man_name", nullable = false)
    private String manufacturerName;

    @Size(max = 255)
    @NotNull
    @Column(name = "man_adr", nullable = false)
    private String manufacturerAddress;

    @Size(max = 255)
    @NotNull
    @Column(name = "man_mobile", nullable = false)
    private String manufacturerMobile;

    @Size(max = 255)
    @NotNull
    @Column(name = "man_email", nullable = false)
    private String manufacturerEmail;

    @OneToMany(mappedBy = "manufacturer")
    private List<Article> articles;
}
