package mk.ukim.finki.db.distributorapp.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mk.ukim.finki.db.distributorapp.weekday.CustomerWeekday;
import mk.ukim.finki.db.distributorapp.orders.Orders;
import mk.ukim.finki.db.distributorapp.users.Users;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "customer")
@DiscriminatorValue("CUSTOMER")
@PrimaryKeyJoinColumn(name = "user_id")
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

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Orders> orders;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<CustomerWeekday> customerWeekdays;
}
