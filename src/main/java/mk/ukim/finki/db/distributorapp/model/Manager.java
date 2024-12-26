package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "manager", schema = "IND0_185022")
public class Manager extends Users{
    @OneToOne
    @JoinColumn(name = "wh_id",nullable = false,unique = true)
    private Warehouse warehouse;
}
