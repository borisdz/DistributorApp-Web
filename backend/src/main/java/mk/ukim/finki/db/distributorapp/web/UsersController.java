package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    private List<UserDto> buildDto(List<mk.ukim.finki.db.distributorapp.model.entities.Users> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (mk.ukim.finki.db.distributorapp.model.entities.Users user : users) {
            UserDto dto = new UserDto(
                    user.getUserId(),
                    user.getUsername(),
                    user.getUserSurname(),
                    user.getUserEmail(),
                    user.getUserMobile(),
                    user.getUserImage(),
                    user.getCity().getCityId(),
                    user.getCity().getCityName(),
                    user.getCity().getRegion().getRegionName(),
                    user.getUserRole(),
                    user.getUserResetToken(),
                    user.getUserResetTokenExpiry()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<Users> users = this.usersService.findAllUsers();
        return ResponseEntity.ok(buildDto(users));
    }

}