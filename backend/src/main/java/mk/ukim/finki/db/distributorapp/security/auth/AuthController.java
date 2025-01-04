package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsersService usersService;

    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }
}
