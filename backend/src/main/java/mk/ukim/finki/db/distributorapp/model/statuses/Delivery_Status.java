package mk.ukim.finki.db.distributorapp.model.statuses;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.Delivery;

import java.util.List;

@Entity
@Data
public class Delivery_Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "del_status_id")
    private Short delivery_status_id;

    @Column(name = "del_status_name", nullable = false)
    private String delivery_status_name;

    @Column(name = "del_status_desc", nullable = false)
    private String delivery_status_description;

    @OneToMany(mappedBy = "deliveryStatus")
    private List<Delivery> deliveries;
}
