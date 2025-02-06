package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String rtoken;
    private LocalDateTime rtoken_exp;
    private String clazz_;
}
