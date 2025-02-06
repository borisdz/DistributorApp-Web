package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "pro_forma")
public class ProForma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_id")
    private Long proFormaId;

    @NotNull
    @Column(name = "pf_deadline", nullable = false)
    private LocalDate proFormaDeadline;

    @NotNull
    @Column(name = "pf_date_created", nullable = false)
    private LocalDate proFormaDateCreated;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pf_status_id", nullable = false)
    private ProFormaStatus proFormaStatus;

    @OneToOne(mappedBy = "proForma")
    private Orders order;
}
