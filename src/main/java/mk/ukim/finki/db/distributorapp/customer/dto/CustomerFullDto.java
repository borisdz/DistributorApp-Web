package mk.ukim.finki.db.distributorapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerFullDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;
    private Integer cityId;
    private String cityName;
    private String regionName;
    private String role;
    private String clazz_;
    private Boolean userActive;
    private String edb;
    private String compName;
    private String address;
    private String repImage;
}
