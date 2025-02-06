package mk.ukim.finki.db.distributorapp.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.*;
import mk.ukim.finki.db.distributorapp.security.auth.AuthService;
import mk.ukim.finki.db.distributorapp.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AuthService authService;
    private final CityService cityService;
    private final WarehouseService warehouseService;
    private final DriverService driverService;
    private final VehicleService vehicleService;

    @GetMapping({"/dashboard","/"})
    public String getDashboard(Model model)
    {
        return "home/admin";
    }

    //    MANAGER CONTROLS:

    @GetMapping("/create-manager")
    public String createManager(Model model) {
        List<CityDtoRegister> cities = this.cityService.findAllCityDtos();
        model.addAttribute("cities", cities);
        model.addAttribute("manager", new CreateManagerDto());
        return "create-manager";
    }

    @PostMapping("/create-manager")
    public String createManager(@ModelAttribute("manager") CreateManagerDto createManagerDto) throws Exception {
        WarehouseDto wh = this.warehouseService.findByCityId(createManagerDto.getCity());
        createManagerDto.setWarehouseId(wh.getId());
        this.authService.createManager(createManagerDto);
        return "redirect:/manager/all";
    }

    @GetMapping("/edit-manager")
    public String editManager() {
        return "edit-manager";
    }

    @GetMapping("/warehouse-by-city")
    @ResponseBody
    public WarehouseDto getWarehouseByCity(@RequestParam("cityId") Integer cityId) {
        return this.warehouseService.findByCityId(cityId);
    }

    //    DRIVER CONTROLS:
    @GetMapping("/create-driver")
    public String createDriver(Model model) {
        List<CityDtoRegister> cities = this.cityService.findAllCityDtos();
        model.addAttribute("cities", cities);
        model.addAttribute("driver", new CreateDriverDto());
        return "create-driver";
    }

    @GetMapping("/vehicle-by-city")
    @ResponseBody
    public List<VehicleBasicDto> getVehicleByCity(@RequestParam("cityId") Integer cityId) {
        WarehouseDto wh = this.warehouseService.findByCityId(cityId);
        return this.vehicleService.getVehiclesByWarehouse(wh.getId());
    }

    @PostMapping("/create-driver")
    public String createDriver(@ModelAttribute("driver") CreateDriverDto createDriverDto, @ModelAttribute("vehicle") VehicleDto vehicleDto) throws Exception {
        this.authService.createDriver(createDriverDto);
        return "all-drivers";
    }

    @GetMapping("/edit-driver/{id}")
    public String editDriver(@PathVariable("id") Long id, Model model) {
        DriverDto driver = this.driverService.findById(id);
        model.addAttribute("driver", driver);
        return "edit-driver";
    }

    @PostMapping("/edit-driver/{id}")
    public String editDriver(@PathVariable("id") Long id, @ModelAttribute("driver") DriverDto driverDto, Model model) {
        Integer res = this.driverService.edit(driverDto);
        if (res == 1) {
            model.addAttribute("edit-success", true);
        } else {
            model.addAttribute("edit-success", false);
        }
        return "all-drivers";
    }


    //    WAREHOUSE CONTROLS:

    @GetMapping("/create-warehouse")
    public String createWarehouse(Model model) {
        List<CityDtoRegister> cities = this.cityService.findAllCityDtos();
        model.addAttribute("warehouse", new WarehouseDto());
        model.addAttribute("cities", cities);
        return "create-warehouse";
    }

    @PostMapping("/create-warehouse")
    public String createWarehouse(@ModelAttribute("warehouse") WarehouseDto warehouseDto, Model model) {
        this.warehouseService.create(warehouseDto);
        return "redirect:/warehouse";
    }
}
