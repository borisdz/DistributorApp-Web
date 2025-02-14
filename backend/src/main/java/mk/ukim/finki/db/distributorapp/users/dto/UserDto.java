package mk.ukim.finki.db.distributorapp.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
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
    Boolean userActive;
}
