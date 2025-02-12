package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.Users;
import mk.ukim.finki.db.distributorapp.users.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/rest/users")
public class RestUsersController {

    private final UsersService usersService;

    public RestUsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    private List<UserDto> buildDto(List<Users> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (Users user : users) {
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
                    user.getUserRole().toString(),
                    user.getClazz_(),
                    user.getUserActive()
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