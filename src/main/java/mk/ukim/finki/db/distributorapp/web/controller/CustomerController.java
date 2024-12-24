package mk.ukim.finki.db.distributorapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/home")
    public String customerHome(Model model) {
        model.addAttribute("welcomeMessage", "Welcome Customer!");
        return "customerHome";
    }
}
