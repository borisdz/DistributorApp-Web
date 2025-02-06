package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersLoadingDto {
    Long userId;
    String userName;
    String userSurname;
    String userPassword;
    String userEmail;
    String userMobile;
    String userSalt;
    Boolean userActive;
    String userImage;
    String userRole;
    String clazz_;
}
