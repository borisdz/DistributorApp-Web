package mk.ukim.finki.db.distributorapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/warehouse")
public class WarehouseController {
    @GetMapping("/create")
    public String createWarehouse() {
        return "create-warehouse";
    }
}
