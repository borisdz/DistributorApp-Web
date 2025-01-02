package mk.ukim.finki.db.distributorapp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "del_status_id")
    @JsonProperty("deliveryStatusId")
    private Short deliveryStatusId;

    @Column(name = "del_status_name", nullable = false)
    @JsonProperty("deliveryStatusName")
    private String deliveryStatusName;

    @Column(name = "del_status_desc", nullable = false)
    @JsonProperty("deliveryStatusDescription")
    private String deliveryStatusDescription;

    @OneToMany(mappedBy = "deliveryStatus")
    private List<Delivery> deliveries;
}
