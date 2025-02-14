package mk.ukim.finki.db.distributorapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String edb;
    private String compName;
    private String address;
    private String repImage;
}
