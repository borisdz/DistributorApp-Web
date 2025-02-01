package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.model.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.dto.VehicleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.security.auth.AuthService;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverController {
    private final DriverService driverService;
    private final AuthService authService;
    private final UsersService usersService;

    public DriverController(DriverService driverService, AuthService authService, UsersService usersService) {
        this.driverService = driverService;
        this.authService = authService;
        this.usersService = usersService;
    }

    @GetMapping("/all")
    public String allDrivers(Model model) {
        List<DriverDto> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        return "all-drivers";
    }

    @GetMapping("/create")
    public String createDriver(Model model) {
        model.addAttribute("driver", new DriverDto());
        model.addAttribute("user", new UserDto());
        return "create-driver";
    }

    @PostMapping("/create")
    public String createDriver(@ModelAttribute("driver") RegisterRequestDto requestDto, @ModelAttribute("vehicle") VehicleDto vehicleDto, Model model) throws Exception {
        DriverDto driverDto = new DriverDto();

        this.authService.register(requestDto);
        Users user = this.usersService.findUserByEmail(requestDto.getEmail());

        driverDto.setId(user.getUserId());
        driverDto.setVehId(vehicleDto.getId());

        Integer res = this.driverService.create(driverDto);
        if (res == 1) {
            model.addAttribute("create-success", true);
        } else {
            model.addAttribute("create-success", false);
        }
        return "all-drivers";
    }

    @GetMapping("/edit/{id}")
    public String editDriver(@PathVariable("id") Long id, Model model) {
        DriverDto driver = this.driverService.findById(id);
        model.addAttribute("driver", driver);
        return "edit-driver";
    }

    @PostMapping("/edit/{id}")
    public String editDriver(@PathVariable("id") Long id, @RequestBody DriverDto driverDto, Model model) {
        Integer res = this.driverService.edit(driverDto);
        if (res == 1) {
            model.addAttribute("edit-success", true);
        } else {
            model.addAttribute("edit-success", false);
        }
        return "all-drivers";
    }
}
