package mk.ukim.finki.db.distributorapp.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.*;
import mk.ukim.finki.db.distributorapp.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
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

    @GetMapping({"/dashboard","/"})
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

    @PostMapping("/create-order")
    public String createOrder(@ModelAttribute CreateOrderDto createOrderDto, Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);
        CustomerDto customer = this.customerService.findCustomerById(user.getId());

        WarehouseDto wh = this.warehouseService.findByCityId(user.getCityId());

        OrdersDto order = new OrdersDto();

        if(createOrderDto.isProForma()){
            ProFormaDto pf = new ProFormaDto();
            pf.setPfDeadline(LocalDate.now().plusWeeks(1));
            pf.setPfDateCreated(LocalDate.now());
            pf.setStatusId((short)1);
            this.proFormaService.create(pf);
        }

        order.setCustomerId(customer.getId());
        order.setOrdDate(LocalDate.now());
        order.setOrdFulfillmentDate(null);
        order.setOStatusId((short) 1);
        order.setDeliveryId(null);
        order.setPfId(null);
        order.setOrdComment(null);
        if(createOrderDto.isProForma()){
            order.setPfId(this.proFormaService.findProFormaById(1L).getId());
        }
        this.ordersService.create(order);

        for(int i=0;i<createOrderDto.getOrderItems().size();i++) {
            Long articleId = createOrderDto.getOrderItems().get(i).getArticleId();
            Integer quantity = createOrderDto.getOrderItems().get(i).getQuantity();

            List<ArticleUnitDto> articleUnitItems = this.articleUnitService.findAllByArticleAndWarehouse(articleId,wh.getId());
            for(int j=0;j<quantity;j++) {
                articleUnitItems.get(j).setOrdId(order.getId());
                this.articleUnitService.edit(articleUnitItems.get(j));
            }
        }

        return "redirect:/customer/my-orders";
    }
}
