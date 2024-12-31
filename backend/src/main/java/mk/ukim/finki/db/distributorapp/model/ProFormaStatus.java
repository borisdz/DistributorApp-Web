package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProFormaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_status_id")
    @JsonProperty("proFormaStatusId")
    private Short proFormaStatusId;

    @Column(name = "pf_status_name", nullable = false)
    @JsonProperty("proFormaStatusName")
    private String proFormaStatusName;

    @Column(name = "pf_status_desc", nullable = false)
    @JsonProperty("proFormaStatusDescription")
    private String proFormaStatusDescription;

    @OneToMany(mappedBy = "proFormaStatus")
    private List<ProForma> pro_formas;
}
