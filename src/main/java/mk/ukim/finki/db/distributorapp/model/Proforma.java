package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Proforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proformaId;

    private String proformaDetails;
    private Double totalAmount;

    @OneToOne(mappedBy = "proforma")
    private Order order;
}
