package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Integer priceId;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "price_eff_date", nullable = false)
    private LocalDateTime priceEffectiveDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "art_id", nullable = false)
    private Article article;

    @ManyToMany
    @JoinTable(name = "unit_price",
            joinColumns = @JoinColumn(name = "price_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private Set<ArticleUnit> articleUnits = new LinkedHashSet<>();
}
