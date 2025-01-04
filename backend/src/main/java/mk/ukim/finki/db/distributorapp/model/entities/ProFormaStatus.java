package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProFormaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_status_id")
    private Short proFormaStatusId;

    @Column(name = "pf_status_name", nullable = false)
    private String proFormaStatusName;

    @Column(name = "pf_status_desc", nullable = false)
    private String proFormaStatusDescription;

    @OneToMany(mappedBy = "proFormaStatus")
    private List<ProForma> pro_formas;
}
