package mk.ukim.finki.db.distributorapp.articleUnit;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.order.Orders;
import mk.ukim.finki.db.distributorapp.price.Price;
import mk.ukim.finki.db.distributorapp.warehouse.Warehouse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "article_unit")
public class ArticleUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long unitId;

    @NotNull
    @Column(name = "unit_expiration_date", nullable = false)
    private Date unitExpirationDate;

    @Size(max = 255)
    @NotNull
    @Column(name = "unit_serial_number", nullable = false)
    private String unitSerialNumber;

    @Size(max = 255)
    @NotNull
    @Column(name = "unit_batch_number", nullable = false)
    private String unitBatchNumber;

    @NotNull
    @Column(name = "unit_manufacture_date", nullable = false)
    private Date unitManufactureDate;

    @NotNull
    @Column(name = "unit_cost_price", nullable = false)
    private BigDecimal unitCostPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wh_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_id")
    private Orders order;

    @ManyToMany
    @JoinTable(
            name = "unit_price",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id")
    )
    private Set<Price> prices = new LinkedHashSet<>();
    // Hm...? Set<Price> or List<Price>
}
