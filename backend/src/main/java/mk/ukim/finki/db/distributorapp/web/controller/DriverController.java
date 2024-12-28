package mk.ukim.finki.db.distributorapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driver")
public class DriverController {

    @GetMapping("/home")
    public String driverHome() {
        return "userDriver/home";
    }
}
