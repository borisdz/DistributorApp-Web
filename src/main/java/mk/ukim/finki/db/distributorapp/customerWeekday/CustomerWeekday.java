package mk.ukim.finki.db.distributorapp.customerWeekday;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.db.distributorapp.customer.Customer;
import mk.ukim.finki.db.distributorapp.weekday.Weekday;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "customer_weekday")
public class CustomerWeekday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_day_id", nullable = false)
    private Long customerDayId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "day_id", nullable = false)
    private Weekday day;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalTime customerDayStartTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalTime customerDayEndTime;

}