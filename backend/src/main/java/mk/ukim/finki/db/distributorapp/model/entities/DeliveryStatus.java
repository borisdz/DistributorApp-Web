package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_status_id")
    private Short deliveryStatusId;

    @Column(name = "d_status_name", nullable = false)
    private String deliveryStatusName;

    @Column(name = "d_status_desc", nullable = false)
    private String deliveryStatusDescription;

    @OneToMany(mappedBy = "deliveryStatus")
    private List<Delivery> deliveries;
}
