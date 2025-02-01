package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.model.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.security.auth.AuthService;
import mk.ukim.finki.db.distributorapp.service.ManagerService;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    private final AuthService authService;
    private final UsersService usersService;

    public ManagerController(ManagerService managerService, AuthService authService, UsersService usersService) {
        this.managerService = managerService;
        this.authService = authService;
        this.usersService = usersService;
    }

    @GetMapping("/create")
    public String createManager(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("manager", new ManagerDto());
        return "create-manager";
    }

    @PostMapping("/create")
    public String createManager(@ModelAttribute("manager") RegisterRequestDto requestDto, Model model) throws Exception {
        ManagerDto managerDto = new ManagerDto();

        this.authService.register(requestDto);
        Users user = this.usersService.findUserByEmail(requestDto.getEmail());

        managerDto.setId(user.getUserId());
        managerDto.setWhId(user.getCity().getWarehouse().getWarehouseId());

        this.managerService.create(managerDto);
        return "redirect:/manager";
    }


    @GetMapping("/edit")
    public String editManager() {
        return "edit-manager";
    }
}
