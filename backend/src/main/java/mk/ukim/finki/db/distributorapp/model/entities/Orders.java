package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
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
    @JoinColumn(name = "ord_status_id", nullable = false)
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
