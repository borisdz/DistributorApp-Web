package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
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

    @Column(name = "pf_deadline", nullable = false)
    private LocalDate proFormaDeadline;

    @Column(name = "pf_date_created", nullable = false)
    private LocalDate proFormaDateCreated;

    @ManyToOne
    @JoinColumn(name = "pf_status_id", nullable = false)
    private ProFormaStatus proFormaStatus;

    @OneToOne(mappedBy = "proForma")
    private Orders order;
}
