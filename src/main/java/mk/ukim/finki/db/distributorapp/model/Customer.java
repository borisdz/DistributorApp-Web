package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "customer", schema = "IND0_185022")
public class Customer extends Users {
    @Column(name = "cust_EDB", nullable = false, length = 13)
    private String customer_EDB;

    @Column(name = "cust_company_name", nullable = false)
    private String customer_company_name;

    @Column(name = "cust_address", nullable = false)
    private String address;

    @Column(name = "cust_open_time", nullable = false)
    private LocalTime customer_open_time;

    @Column(name = "cust_close_time", nullable = false)
    private LocalTime customer_close_time;

    @Column(name = "cust_representative_img", nullable = false)
    private String customer_representative_image;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;
}
