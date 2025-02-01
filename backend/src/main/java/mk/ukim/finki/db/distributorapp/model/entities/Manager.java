package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "manager")
@DiscriminatorValue(value = "Manager")
public class Manager extends Users {

    @NotNull
    @OneToOne
    @JoinColumn(name = "wh_id", nullable = false, unique = true)
    private Warehouse warehouse;
}
