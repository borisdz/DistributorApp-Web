package mk.ukim.finki.db.distributorapp.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private Integer vehId;
}
