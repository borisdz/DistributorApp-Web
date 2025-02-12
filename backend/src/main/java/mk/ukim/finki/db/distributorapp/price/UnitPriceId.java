package mk.ukim.finki.db.distributorapp.price;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UnitPriceId implements Serializable {
    @Column(name = "unit_id", nullable = false)
    private Long unitId;
    @Column(name = "price_id", nullable = false)
    private Long priceId;

}