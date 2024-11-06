package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_article",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proforma_id",referencedColumnName = "proformaId")
    private Proforma proforma;

    @ManyToOne
    @JoinColumn(name = "delivery_id",nullable = false)
    private Delivery delivery;
}
