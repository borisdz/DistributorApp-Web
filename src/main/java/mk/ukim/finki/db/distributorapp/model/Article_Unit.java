package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Article_Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_id;

    @Column(name = "unit_expiration_date", nullable = false)
    private Date unit_expiration_date;

    @Column(name = "unit_serial_number", nullable = false)
    private String unit_serial_number;

    @Column(name = "unit_batch_number", nullable = false)
    private String unit_batch_number;

    @Column(name = "unit_manufacture_date", nullable = false)
    private Date unit_manufacture_date;

    @Column(name = "unit_cost_price", nullable = false)
    private double unit_cost_price;

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
