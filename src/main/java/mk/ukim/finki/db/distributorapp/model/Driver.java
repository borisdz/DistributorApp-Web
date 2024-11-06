package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Driver extends User {
    @OneToOne
    @JoinColumn(name = "vehicle_id",referencedColumnName = "vehicleId")
    private Vehicle vehicle;
}
