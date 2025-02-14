package mk.ukim.finki.db.distributorapp.weekday;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.customerWeekday.CustomerWeekday;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "weekday")
public class Weekday {
    @Id
    @Column(name = "day_id", nullable = false)
    private Short id;

    @Size(max = 20)
    @NotNull
    @Column(name = "day_name", nullable = false, length = 20)
    private String dayName;

    @OneToMany(mappedBy = "day")
    private Set<CustomerWeekday> customerWeekdays = new LinkedHashSet<>();

}