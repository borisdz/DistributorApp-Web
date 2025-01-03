package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.Data;

@Data
public class UsersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;
    private Long cityId;
    private String cityName;
    private String regionName;
}
