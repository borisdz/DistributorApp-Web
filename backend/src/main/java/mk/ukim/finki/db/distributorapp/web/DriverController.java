package mk.ukim.finki.db.distributorapp.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.*;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.security.auth.AuthService;
import mk.ukim.finki.db.distributorapp.service.CityService;
import mk.ukim.finki.db.distributorapp.service.DeliveryService;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/driver")
public class DriverController {
    private final DriverService driverService;
    private final AuthService authService;
    private final UsersService usersService;
    private final DeliveryService deliveryService;
    private final CityService cityService;

    @GetMapping("/all")
    public String allDrivers(Model model) {
        List<DriverDto> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        return "all-drivers";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Users user = this.usersService.findUserByEmail(model.getAttribute("email").toString());
        Driver driver = this.driverService.getDriverObjById(user.getUserId());
        model.addAttribute("newDeliveries", deliveryService.getAllNewDeliveriesByDriver(driver));
        model.addAttribute("doneDeliveries", deliveryService.getAllDeliveriesByDriver(driver));
        return "home/driver";
    }


}
