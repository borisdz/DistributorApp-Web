package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.enumerations.Role;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;
    private Long cityId;
    private String cityName;
    private String regionName;
    private Role role;
}
