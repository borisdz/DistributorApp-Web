package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
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