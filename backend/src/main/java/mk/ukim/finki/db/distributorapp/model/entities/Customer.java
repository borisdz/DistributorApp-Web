package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "customer")
@DiscriminatorValue("CUSTOMER")
public class Customer extends Users {
    @Size(max = 13)
    @NotNull
    @Column(name = "cust_EDB", nullable = false, length = 13)
    private String customerEDB;

    @Size(max = 255)
    @NotNull
    @Column(name = "cust_company_name", nullable = false)
    private String customerCompanyName;

    @Size(max = 255)
    @NotNull
    @Column(name = "cust_adr", nullable = false)
    private String customerAddress;

    @Size(max = 255)
    @NotNull
    @Column(name = "cust_representative_img")
    private String customerRepresentativeImage;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    @OneToMany(mappedBy = "customer")
    private List<CustomerWeekday> customerWeekdays;
}
