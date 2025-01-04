package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "price_eff_date", nullable = false)
    private LocalDateTime priceEffectiveDate;

    @ManyToOne
    @JoinColumn(name = "art_id", nullable = false)
    private Article article;

}
