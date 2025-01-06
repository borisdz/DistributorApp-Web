package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_status_id")
    private Short orderStatusId;

    @Column(name = "o_status_name", nullable = false)
    private String orderStatusName;

    @Column(name = "o_status_desc", nullable = false)
    private String orderStatusDescription;

    @OneToMany(mappedBy = "orderStatus")
    private List<Orders> orders;
}
