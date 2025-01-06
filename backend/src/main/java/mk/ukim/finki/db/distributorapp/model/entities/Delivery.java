package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "del_id")
    private Long deliveryId;

    @Column(name = "del_date_created", nullable = false)
    private LocalDate deliveryDateCreated;

    @Column(name = "del_date", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "del_start_km")
    private Integer deliveryStartKm;

    @Column(name = "del_end_km")
    private Integer deliveryEndKm;

    @Column(name = "del_start_time")
    private LocalTime deliveryStartTime;

    @Column(name = "del_end_time")
    private LocalTime deliveryEndTime;

    @ManyToOne
    @JoinColumn(name = "d_status_id", nullable = false)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "veh_id", nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "delivery")
    private List<Orders> orders;
}
