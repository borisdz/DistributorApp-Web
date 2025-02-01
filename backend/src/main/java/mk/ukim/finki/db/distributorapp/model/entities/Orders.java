package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "ord_date", nullable = false)
    private LocalDate orderDate;

    @NotNull
    @Column(name = "ord_sum", nullable = false)
    private Integer orderSum;

    @Column(name = "ord_fulfillment_date")
    private LocalDateTime orderFulfillmentDate;

    @Column(name = "ord_comment")
    private String orderComment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "o_status_id", nullable = false)
    private OrderStatus orderStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "del_id", nullable = false)
    private Delivery delivery;

    @OneToOne
    @JoinColumn(name = "pf_id", nullable = false, unique = true)
    private ProForma proForma;

    @OneToMany(mappedBy = "order")
    private List<ArticleUnit> articleUnits;
}
