package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManufacturerDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
}
