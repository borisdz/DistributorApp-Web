package mk.ukim.finki.db.distributorapp.orders;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="order_status", schema = "IND0_185022")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_status_id")
    private Short orderStatusId;

    @Size(max = 255)
    @NotNull
    @Column(name = "o_status_name", nullable = false)
    private String orderStatusName;

    @NotNull
    @Column(name = "o_status_desc", nullable = false)
    private String orderStatusDescription;

    @OneToMany(mappedBy = "orderStatus")
    private List<Orders> orders;
}
