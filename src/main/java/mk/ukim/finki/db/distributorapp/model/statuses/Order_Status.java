package mk.ukim.finki.db.distributorapp.model.statuses;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.Orders;

import java.util.List;

@Entity
@Data
public class Order_Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_status_id")
    private Short order_status_id;

    @Column(name = "ord_status_name", nullable = false)
    private String order_status_name;

    @Column(name = "ord_status_desc", nullable = false)
    private String order_status_description;

    @OneToMany(mappedBy = "order_status")
    private List<Orders> orders;
}
