package mk.ukim.finki.db.distributorapp.delivery;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.orders.Orders;
import mk.ukim.finki.db.distributorapp.vehicle.Vehicle;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "del_id")
    private Long deliveryId;

    @NotNull
    @Column(name = "del_date_created", nullable = false)
    private Date deliveryDateCreated;

    @NotNull
    @Column(name = "del_date", nullable = false)
    private Date deliveryDate;

    @Column(name = "del_start_km")
    private Integer deliveryStartKm;

    @Column(name = "del_end_km")
    private Integer deliveryEndKm;

    @Column(name = "del_start_time")
    private LocalTime deliveryStartTime;

    @Column(name = "del_end_time")
    private LocalTime deliveryEndTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "d_status_id", nullable = false)
    private DeliveryStatus deliveryStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "veh_id", nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "delivery")
    private List<Orders> orders;
}
