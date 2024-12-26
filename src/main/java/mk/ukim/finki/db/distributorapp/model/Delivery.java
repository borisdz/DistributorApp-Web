package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.statuses.Delivery_Status;

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
    private Long delivery_id;

    @Column(name = "del_date_created", nullable = false)
    private LocalDate delivery_date_created;

    @Column(name = "del_date", nullable = false)
    private LocalDate delivery_date;

    @Column(name = "del_start_km")
    private Integer delivery_start_km;

    @Column(name = "del_end_km")
    private Integer delivery_end_km;

    @Column(name = "del_start_time")
    private LocalTime delivery_start_time;

    @Column(name = "del_end_time")
    private LocalTime delivery_end_time;

    @ManyToOne
    @JoinColumn(name = "del_status_id", nullable = false)
    private Delivery_Status delivery_status;

    @ManyToOne
    @JoinColumn(name = "veh_id", nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "delivery")
    private List<Orders> orders;
}
