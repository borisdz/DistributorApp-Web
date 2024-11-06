package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @OneToOne(mappedBy = "warehouse")
    private Manager manager;
}
