package mk.ukim.finki.db.distributorapp.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDriverDto {
    private String name;
    private String surname;
    private String password;
    private String repeatedPassword;
    private String email;
    private String mobile;
    private String profileImage;
    private Integer city;
    private Integer vehicle;
}
