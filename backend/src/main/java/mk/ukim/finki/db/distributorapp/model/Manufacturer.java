package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "manufacturer", schema = "IND0_185022")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "man_id")
    @JsonProperty("manufacturerId")
    private Long manufacturerId;

    @Column(name = "man_name", nullable = false)
    @JsonProperty("manufacturerName")
    private String manufacturerName;

    @Column(name = "man_address", nullable = false)
    @JsonProperty("manufacturerAddress")
    private String manufacturerAddress;

    @Column(name = "man_mobile", nullable = false)
    @JsonProperty("manufacturerMobile")
    private String manufacturerMobile;

    @Column(name = "man_email", nullable = false)
    @JsonProperty("manufacturerEmail")
    private String manufacturerEmail;

    @OneToMany(mappedBy = "manufacturer")
    private List<Article> articles;
}
