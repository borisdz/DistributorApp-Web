package mk.ukim.finki.db.distributorapp.manager;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mk.ukim.finki.db.distributorapp.users.Users;
import mk.ukim.finki.db.distributorapp.warehouse.Warehouse;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "manager")
@DiscriminatorValue("MANAGER")
@PrimaryKeyJoinColumn(name = "user_id")
public class Manager extends Users {
    @NotNull
    @OneToOne
    @JoinColumn(name = "wh_id", nullable = false, unique = true)
    private Warehouse warehouse;
}