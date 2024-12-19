package mk.ukim.finki.db.distributorapp.model.statuses;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.Pro_Forma;

import java.util.List;

@Entity
@Data
public class Pro_Forma_Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_status_id")
    private Short pro_forma_status_id;

    @Column(name="pf_status_name", nullable = false)
    private String pro_forma_status_name;

    @Column(name="pf_status_desc", nullable = false)
    private String pro_forma_status_description;

    @OneToMany(mappedBy = "pro_forma_status")
    private List<Pro_Forma> pro_formas;
}