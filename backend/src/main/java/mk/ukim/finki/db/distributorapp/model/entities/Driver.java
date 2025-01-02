package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "driver")
public class Driver extends Users {
    @OneToOne
    @JoinColumn(name = "veh_id", nullable = false)
    private Vehicle vehicle;
}
