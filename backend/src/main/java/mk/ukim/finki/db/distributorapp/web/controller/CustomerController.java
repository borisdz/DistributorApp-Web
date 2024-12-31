package mk.ukim.finki.db.distributorapp.web.controller;

import mk.ukim.finki.db.distributorapp.model.Customer;
import mk.ukim.finki.db.distributorapp.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = this.customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer c = this.customerService.findCustomerById(id).get();
        return ResponseEntity.ok(c);
    }

    @GetMapping("/home")
    public String customerHome() {
        return "userCustomer/home";
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = this.customerService.create(
                customer.getCustomerEDB(),
                customer.getCustomerCompanyName(),
                customer.getCustomerAddress(),
                customer.getCustomerOpenTime(),
                customer.getCustomerCloseTime(),
                customer.getCustomerRepresentativeImage()).get();

        return ResponseEntity.ok(newCustomer);
    }

    @PutMapping("/edit")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updateCustomer = this.customerService.edit(
                customer.getUserId(),
                customer.getCustomerEDB(),
                customer.getCustomerCompanyName(),
                customer.getCustomerAddress(),
                customer.getCustomerOpenTime(),
                customer.getCustomerCloseTime(),
                customer.getCustomerRepresentativeImage()).get();

        return ResponseEntity.ok(updateCustomer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        this.customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
