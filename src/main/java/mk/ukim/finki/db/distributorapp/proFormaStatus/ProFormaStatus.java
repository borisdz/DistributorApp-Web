package mk.ukim.finki.db.distributorapp.proFormaStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.proForma.ProForma;

import java.util.List;

@Entity
@Data
@Table(name = "pro_forma_status", schema = "IND0_185022")
public class ProFormaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_status_id")
    private Short proFormaStatusId;

    @Size(max = 255)
    @NotNull
    @Column(name = "pf_status_name", nullable = false)
    private String proFormaStatusName;

    @NotNull
    @Column(name = "pf_status_desc", nullable = false)
    private String proFormaStatusDescription;

    @OneToMany(mappedBy = "proFormaStatus")
    private List<ProForma> pro_formas;
}
