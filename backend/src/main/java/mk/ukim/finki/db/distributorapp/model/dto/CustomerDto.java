package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

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
    private LocalTime openTime;
    private LocalTime closeTime;
    private String repImage;
}
