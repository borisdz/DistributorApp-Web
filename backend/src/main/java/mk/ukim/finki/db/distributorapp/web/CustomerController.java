package mk.ukim.finki.db.distributorapp.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.*;
import mk.ukim.finki.db.distributorapp.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final UsersService usersService;
    private final OrdersService ordersService;
    private final DeliveryService deliveryService;
    private final CategoryService categoryService;
    private final WarehouseService warehouseService;
    private final ArticleService articleService;
    private final ArticleUnitService articleUnitService;
    private final ManufacturerService manufacturerService;
    private final ProFormaService proFormaService;

    @GetMapping({"/dashboard", "/"})
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);
        CustomerDto customer = this.customerService.findCustomerById(user.getId());
        model.addAttribute("customer", customer);
        model.addAttribute("currentOrders", ordersService.findCurrentOrdersByCustomer(customer.getId()));
        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByCustomer(customer.getId()));
        return "home/customer";
    }

    @GetMapping("/my-orders")
    public String myOrders(Model model) {
        return "my-orders";
    }

    @GetMapping("/create-order")
    public String createOrder(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userEmail = authentication.getName();

        UserDto user = this.usersService.findUserDtoByEmail(userEmail);

        WarehouseDto warehouse = this.warehouseService.findByCityId(user.getCityId());

        List<CategoryDto> categories = this.categoryService.listCategories();
        List<ArticleDto> articles = this.articleService.getAllArticlesByWarehouse(warehouse.getId());
        List<ManufacturerDto> manufacturers = this.manufacturerService.getAllManufacturers();
        List<ArticleUnitDto> articleUnits = this.articleUnitService.getAllArticleUnitsByWarehouse(warehouse.getId());

        model.addAttribute("categories", categories);
        model.addAttribute("articles", articles);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("articleUnits", articleUnits);
        return "create-order";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        ObjectMapper objectMapper = new ObjectMapper();
        binder.registerCustomEditor(List.class, "orderItems", new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    List<OrderItemDto> items = objectMapper.readValue(
                            text, new TypeReference<List<OrderItemDto>>() {
                            }
                    );
                    setValue(items);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid JSON for order items", e);
                }
            }
        });
    }

    @PostMapping("/create-order")
    public String createOrder(@ModelAttribute CreateOrderDto createOrderDto, Principal principal) {

        this.ordersService.create(createOrderDto, principal);

        String userEmail = principal.getName();

        UserDto user = this.usersService.findUserDtoByEmail(userEmail);

        CustomerDto customer = this.customerService.findCustomerById(user.getId());

        List<OrderSimpleDto> simpleCustomerOrders = this.ordersService.findSimpleOrdersByCustoemr(customer.getId());
        OrderSimpleDto createdOrder = simpleCustomerOrders.get(simpleCustomerOrders.size() - 1);
        WarehouseDto wh = this.warehouseService.findByCityId(user.getCityId());

        List<ArticleUnitSimpleDto> editedUnits = this.articleUnitService.addArticleUnitToOrder(createOrderDto.getOrderItems(), createdOrder.getId(), wh.getId());

        for (ArticleUnitSimpleDto unit : editedUnits) {
            this.articleUnitService.simpleEdit(unit);
        }

        return "redirect:/customer/my-orders";
    }
}
