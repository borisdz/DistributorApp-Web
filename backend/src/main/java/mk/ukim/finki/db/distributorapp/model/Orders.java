package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

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
    @JsonProperty("orderId")
    private Long orderId;

    @Column(name = "ord_date", nullable = false)
    @JsonProperty("orderDate")
    private LocalDate orderDate;

    @Column(name = "ord_sum", nullable = false)
    @JsonProperty("orderSum")
    private Integer orderSum;

    @Column(name = "ord_fulfillment_date")
    @JsonProperty("orderFulfillmentDate")
    private LocalDateTime orderFulfillmentDate;

    @Column(name = "ord_comment")
    @JsonProperty("orderComment")
    private String orderComment;

    @ManyToOne
    @JoinColumn(name = "ord_status_id",nullable = false)
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
