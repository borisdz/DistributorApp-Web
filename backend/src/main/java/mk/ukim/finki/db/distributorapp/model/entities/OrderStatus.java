package mk.ukim.finki.db.distributorapp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_status_id")
    @JsonProperty("orderStatusId")
    private Short orderStatusId;

    @Column(name = "ord_status_name", nullable = false)
    @JsonProperty("orderStatusName")
    private String orderStatusName;

    @Column(name = "ord_status_desc", nullable = false)
    @JsonProperty("orderStatusDescription")
    private String orderStatusDescription;

    @OneToMany(mappedBy = "orderStatus")
    private List<Orders> orders;
}
