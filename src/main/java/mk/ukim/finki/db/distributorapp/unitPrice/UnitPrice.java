package mk.ukim.finki.db.distributorapp.unitPrice;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.articleUnit.ArticleUnit;
import mk.ukim.finki.db.distributorapp.price.Price;

@Data
@Entity
@Table(name = "unit_price", schema = "IND0_185022")
public class UnitPrice {
    @EmbeddedId
    private UnitPriceId id;

    @MapsId("unitId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private ArticleUnit unit;

    @MapsId("priceId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

}