package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.statuses.Pro_Forma_Status;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "pro_forma", schema = "IND0_185022")
public class Pro_Forma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_id")
    private Long pro_forma_id;

    @Column(name = "pf_deadline", nullable = false)
    private LocalDate pro_forma_deadline;

    @Column(name = "pf_date_created", nullable = false)
    private LocalDate pro_forma_date_created;

    @ManyToOne
    @JoinColumn(name = "pf_status_id", nullable = false)
    private Pro_Forma_Status pro_forma_status;

    @OneToOne(mappedBy = "proForma")
    private Orders order;
}
