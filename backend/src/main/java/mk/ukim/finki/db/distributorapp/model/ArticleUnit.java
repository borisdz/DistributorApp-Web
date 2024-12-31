package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "article_unit", schema = "IND0_185022")
public class ArticleUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("unitId")
    private Long unitId;

    @Column(name = "unit_expiration_date", nullable = false)
    @JsonProperty("unitExpirationDate")
    private Date unitExpirationDate;

    @Column(name = "unit_serial_number", nullable = false)
    @JsonProperty("unitSerialNumber")
    private String unitSerialNumber;

    @Column(name = "unit_batch_number", nullable = false)
    @JsonProperty("unitBatchNumber")
    private String unitBatchNumber;

    @Column(name = "unit_manufacture_date", nullable = false)
    @JsonProperty("unitManufactureDate")
    private Date unitManufactureDate;

    @Column(name = "unit_cost_price", nullable = false)
    @JsonProperty("unitCostPrice")
    private Double unitCostPrice;

    @ManyToOne
    @JoinColumn(name = "art_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "wh_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "ord_id")
    private Orders order;
}
