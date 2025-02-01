package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_status_id")
    private Short deliveryStatusId;

    @Size(max = 255)
    @NotNull
    @Column(name = "d_status_name", nullable = false)
    private String deliveryStatusName;

    @NotNull
    @Column(name = "d_status_desc", nullable = false)
    private String deliveryStatusDescription;

    @OneToMany(mappedBy = "deliveryStatus")
    private List<Delivery> deliveries;
}
