package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "price", schema = "IND0_185022")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    @JsonProperty("priceId")
    private Integer priceId;

    @Column(name = "price", nullable = false)
    @JsonProperty("price")
    private BigDecimal price;

    @Column(name = "price_eff_date", nullable = false)
    @JsonProperty("priceEffectiveDate")
    private LocalDateTime priceEffectiveDate;

    @ManyToOne
    @JoinColumn(name = "art_id", nullable = false)
    private Article article;

}
