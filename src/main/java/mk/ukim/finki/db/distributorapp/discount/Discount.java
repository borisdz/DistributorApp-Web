package mk.ukim.finki.db.distributorapp.discount;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.proForma.ProForma;

@Entity
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long discountId;

    @NotNull
    @Column(name = "dsc_amount", nullable = false)
    private Integer discountAmount;

    @OneToOne(mappedBy="discount")
    private ProForma proForma;
}
