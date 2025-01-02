package mk.ukim.finki.db.distributorapp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("weekdayId")
    private Short id;

    @Column(name = "day_name", nullable = false, length = 20)
    @JsonProperty("dayName")
    private String dayName;

    @OneToMany(mappedBy = "day")
    private Set<CustomerWeekday> customerWeekdays = new LinkedHashSet<>();

}