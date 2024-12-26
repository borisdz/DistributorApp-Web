package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.statuses.Order_Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders", schema = "IND0_185022")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id", nullable = false)
    private Long order_id;

    @Column(name = "ord_date", nullable = false)
    private LocalDate order_date;

    @Column(name = "ord_sum", nullable = false)
    private Integer order_sum;

    @Column(name = "ord_fulfillment_date")
    private LocalDateTime order_fulfillment_date;

    @Column(name = "ord_comment")
    private String order_comment;

    @ManyToOne
    @JoinColumn(name = "ord_status_id")
    private Order_Status order_status;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "del_id", nullable = false)
    private Delivery delivery;

    @OneToOne
    @JoinColumn(name = "pf_id", nullable = false, unique = true)
    private Pro_Forma proForma;

    @OneToMany(mappedBy = "order")
    private List<Article_Unit> articleUnits;
}
