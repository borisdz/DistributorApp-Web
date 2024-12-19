package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Manager extends Users{
    @OneToOne
    @JoinColumn(name = "wh_id",nullable = false,unique = true)
    private Warehouse warehouse;
}
