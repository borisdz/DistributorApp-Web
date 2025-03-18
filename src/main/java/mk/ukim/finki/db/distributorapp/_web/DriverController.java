package mk.ukim.finki.db.distributorapp._web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryEndDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryStartDto;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.driver.DriverService;
import mk.ukim.finki.db.distributorapp.users.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/driver")
public class DriverController {
    private final DriverService driverService;
    private final UserService userService;

    @GetMapping({"/dashboard", "/"})
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        model.addAttribute("user", user);
        model.addAttribute("startDelivery", new DeliveryStartDto());
        model.addAttribute("endDelivery", new DeliveryEndDto());
        model.addAttribute("ongoingDeliveries", this.driverService.getOngoingDeliveries(user.getId()));
        model.addAttribute("newDeliveries", this.driverService.getNewAssignedDeliveries(user.getId()));
        model.addAttribute("finishedDeliveries", this.driverService.getFinishedAssignedDeliveries(user.getId()));
        return "home/driver";
    }

    @PostMapping("/start-delivery")
    public String startDelivery(@ModelAttribute("startDelivery") DeliveryStartDto delivery) {
        this.driverService.startDelivery(delivery);
        return "redirect:/driver/dashboard";
    }

    @PostMapping("/end-delivery")
    public String endDelivery(@ModelAttribute("endDelivery") DeliveryEndDto delivery) {
        this.driverService.endDelivery(delivery);
        return "redirect:/driver/dashboard";
    }


}
