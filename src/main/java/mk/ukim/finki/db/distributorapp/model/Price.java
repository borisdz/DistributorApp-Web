package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Integer price_id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "price_eff_date", nullable = false)
    private LocalDateTime price_effective_date;

    @ManyToOne
    @JoinColumn(name = "art_id",nullable = false)
    private Article article;

}
