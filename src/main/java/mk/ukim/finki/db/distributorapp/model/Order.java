package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.enumerations.OrderStatus;

import java.util.List;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Double amount;
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_article",
            joinColumns = @JoinColumn(name = "order_id",referencedColumnName = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "article_id",referencedColumnName = "articleId")
    )
    private List<Article> articles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proforma_id",referencedColumnName = "proformaId")
    private Proforma proforma;

    @ManyToOne
    @JoinColumn(name = "delivery_id",nullable = false,referencedColumnName = "deliveryId")
    private Delivery delivery;

    public Order(Long customerId, List<Article> articles) {
        this.customer = customer;
        this.articles = articles;
    }
}
