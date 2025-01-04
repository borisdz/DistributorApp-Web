package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "del_status_id")
    private Short deliveryStatusId;

    @Column(name = "del_status_name", nullable = false)
    private String deliveryStatusName;

    @Column(name = "del_status_desc", nullable = false)
    private String deliveryStatusDescription;

    @OneToMany(mappedBy = "deliveryStatus")
    private List<Delivery> deliveries;
}
