package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "driver")
@DiscriminatorValue("DRIVER")
@PrimaryKeyJoinColumn(name = "user_id")
public class Driver extends Users {
    @NotNull
    @OneToOne
    @JoinColumn(name = "veh_id", nullable = false)
    private Vehicle vehicle;
}
