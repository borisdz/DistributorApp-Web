package mk.ukim.finki.db.distributorapp._security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
    String email;
    String oldPassword;
    String newPassword;
}
