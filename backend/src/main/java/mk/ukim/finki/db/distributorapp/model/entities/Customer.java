package mk.ukim.finki.db.distributorapp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "customer")
public class Customer extends Users {
    @Column(name = "cust_EDB", nullable = false, length = 13)
    @JsonProperty("customerEDB")
    private String customerEDB;

    @Column(name = "cust_company_name", nullable = false)
    @JsonProperty("customerCompanyName")
    private String customerCompanyName;

    @Column(name = "cust_address", nullable = false)
    @JsonProperty("customerAddress")
    private String customerAddress;

    @Column(name = "cust_open_time", nullable = false)
    @JsonProperty("customerOpenDate")
    private LocalTime customerOpenTime;

    @Column(name = "cust_close_time", nullable = false)
    @JsonProperty("customerCloseTime")
    private LocalTime customerCloseTime;

    @Column(name = "cust_representative_img", nullable = false)
    @JsonProperty("customerRepresentativeImage")
    private String customerRepresentativeImage;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;
}
