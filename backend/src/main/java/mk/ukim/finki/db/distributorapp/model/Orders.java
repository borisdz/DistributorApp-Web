package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.statuses.OrderStatus;

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
    private Long orderId;

    @Column(name = "ord_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "ord_sum", nullable = false)
    private Integer orderSum;

    @Column(name = "ord_fulfillment_date")
    private LocalDateTime orderFulfillmentDate;

    @Column(name = "ord_comment")
    private String orderComment;

    @ManyToOne
    @JoinColumn(name = "ord_status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "del_id", nullable = false)
    private Delivery delivery;

    @OneToOne
    @JoinColumn(name = "pf_id", nullable = false, unique = true)
    private ProForma proForma;

    @OneToMany(mappedBy = "order")
    private List<ArticleUnit> articleUnits;
}
