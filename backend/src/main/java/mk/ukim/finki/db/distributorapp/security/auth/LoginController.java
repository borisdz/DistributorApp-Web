//package mk.ukim.finki.db.distributorapp.web;
//
//import jakarta.servlet.http.HttpServletRequest;
//import mk.ukim.finki.db.distributorapp.model.entities.Users;
//import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidUserCredentialsException;
//import mk.ukim.finki.db.distributorapp.service.UsersService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//    private final UsersService usersService;
//
//    public LoginController(UsersService usersService) {
//        this.usersService = usersService;
//    }
//
//    @GetMapping
//    public String getLoginPage() {
//        return "login";
//    }
//
//    @PostMapping
//    public String login(HttpServletRequest req, Model model) {
//        Users user = null;
//        try {
//            user = this.usersService.login(req.getParameter("email"), req.getParameter("password"));
//            return "redirect:/home";
//        } catch (InvalidUserCredentialsException e) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", e.getMessage());
//            return "login";
//        }
//    }
//}
