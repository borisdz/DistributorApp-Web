package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Driver extends User {

    @OneToOne
    private Vehicle vehicle;
}