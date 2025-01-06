package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "weekday")
public class Weekday {
    @Id
    @Column(name = "day_id", nullable = false)
    private Short id;

    @Column(name = "day_name", nullable = false, length = 20)
    private String dayName;

    @OneToMany(mappedBy = "day")
    private Set<CustomerWeekday> customerWeekdays = new LinkedHashSet<>();

}