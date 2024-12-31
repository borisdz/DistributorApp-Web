package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Table(name = "delivery", schema = "IND0_185022")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "del_id")
    @JsonProperty("deliveryId")
    private Long deliveryId;

    @Column(name = "del_date_created", nullable = false)
    @JsonProperty("deliveryDateCreated")
    private LocalDate deliveryDateCreated;

    @Column(name = "del_date", nullable = false)
    @JsonProperty("deliveryDate")
    private LocalDate deliveryDate;

    @Column(name = "del_start_km")
    @JsonProperty("deliveryStartKm")
    private Integer deliveryStartKm;

    @Column(name = "del_end_km")
    @JsonProperty("deliveryEndKm")
    private Integer deliveryEndKm;

    @Column(name = "del_start_time")
    @JsonProperty("deliveryStartTime")
    private LocalTime deliveryStartTime;

    @Column(name = "del_end_time")
    @JsonProperty("deliveryEndTime")
    private LocalTime deliveryEndTime;

    @ManyToOne
    @JoinColumn(name = "del_status_id", nullable = false)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "veh_id", nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "delivery")
    private List<Orders> orders;
}
