package mk.ukim.finki.db.distributorapp.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/driver")
public class DriverController {
    private final DriverService driverService;
    private final UsersService usersService;

    @GetMapping("/all")
    public String allDrivers(Model model) {
//        List<DriverDto> drivers = driverService.getAllDrivers();
//        model.addAttribute("drivers", drivers);
        return "all-drivers";
    }

    @GetMapping({"/dashboard","/"})
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);
        model.addAttribute("user", user);
        model.addAttribute("newDeliveries", this.driverService.getNewAssignedDeliveries(user.getId()));
        model.addAttribute("finishedDeliveries", this.driverService.getFinishedAssignedDeliveries(user.getId()));
        return "home/driver";
    }


}
