package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Manager extends User {
    @OneToOne
    @JoinColumn(name = "warehouse_id",referencedColumnName = "warehouseId")
    private Warehouse warehouse;
}
