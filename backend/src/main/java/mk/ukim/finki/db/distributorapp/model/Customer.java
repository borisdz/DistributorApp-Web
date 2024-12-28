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
    private String customerEDB;

    @Column(name = "cust_company_name", nullable = false)
    private String customerCompanyName;

    @Column(name = "cust_address", nullable = false)
    private String customerAddress;

    @Column(name = "cust_open_time", nullable = false)
    private LocalTime customerOpenTime;

    @Column(name = "cust_close_time", nullable = false)
    private LocalTime customerCloseTime;

    @Column(name = "cust_representative_img", nullable = false)
    private String customerRepresentativeImage;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;
}
