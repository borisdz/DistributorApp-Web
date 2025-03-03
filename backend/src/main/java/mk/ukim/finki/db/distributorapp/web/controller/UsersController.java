package mk.ukim.finki.db.distributorapp.web.controller;

import mk.ukim.finki.db.distributorapp.model.Users;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = this.usersService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<Users> findUserByEmail(@PathVariable("email") String email) {
        Users user = this.usersService.getUserByEmail(email).get();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
      return ResponseEntity.ok(user);
    }


}
