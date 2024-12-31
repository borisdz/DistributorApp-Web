package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "pro_forma", schema = "IND0_185022")
public class ProForma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_id")
    @JsonProperty("proFormaId")
    private Long proFormaId;

    @Column(name = "pf_deadline", nullable = false)
    @JsonProperty("proFormaDeadline")
    private LocalDate proFormaDeadline;

    @Column(name = "pf_date_created", nullable = false)
    @JsonProperty("proFormaDateCreated")
    private LocalDate proFormaDateCreated;

    @ManyToOne
    @JoinColumn(name = "pf_status_id", nullable = false)
    private ProFormaStatus proFormaStatus;

    @OneToOne(mappedBy = "proForma")
    private Orders order;
}
