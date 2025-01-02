package mk.ukim.finki.db.distributorapp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "customer_weekday")
public class CustomerWeekday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_day_id", nullable = false)
    @JsonProperty("customerDayId")
    private Long customerDayId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "day_id", nullable = false)
    private Weekday day;

    @Column(name = "start_time", nullable = false)
    private LocalTime customerDayStartTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime customerDayEndTime;

}