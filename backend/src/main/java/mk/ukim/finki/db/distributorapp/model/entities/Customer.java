package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "customer")
@DiscriminatorValue(value = "Customer")
public class Customer extends Users {
    @Column(name = "cust_EDB", nullable = false, length = 13)
    private String customerEDB;

    @Column(name = "cust_company_name", nullable = false)
    private String customerCompanyName;

    @Column(name = "cust_adr", nullable = false)
    private String customerAddress;

    @Column(name = "cust_representative_img", nullable = false)
    private String customerRepresentativeImage;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;
}
