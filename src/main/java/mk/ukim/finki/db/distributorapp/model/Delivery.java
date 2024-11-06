package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import mk.ukim.finki.db.distributorapp.model.enumerations.DeliveryStatus;

import java.util.Date;
import java.util.List;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;
    private Date dateCreated;
    @Enumerated
    private DeliveryStatus deliveryStatus;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;
}
