package mk.ukim.finki.db.distributorapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("/home")
    public String managerHome(Model model) {
        model.addAttribute("welcomeMessage", "Welcome Manager!");
        return "managerHome";
    }
}
