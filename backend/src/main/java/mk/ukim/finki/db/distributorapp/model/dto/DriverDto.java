package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private Integer vehId;
}
