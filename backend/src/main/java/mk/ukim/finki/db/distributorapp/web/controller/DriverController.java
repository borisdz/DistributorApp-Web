package mk.ukim.finki.db.distributorapp.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @GetMapping("/home")
    public String driverHome() {
        return "userDriver/home";
    }
}
