package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.model.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/customer")
public class RestCustomerController {

    private final CustomerService customerService;

    public RestCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> result = customerService.findAllCustomers();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addCustomer(@RequestBody CustomerDto customerDto) {
        Integer result = this.customerService.create(customerDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<Integer> editCustomer(@RequestBody CustomerDto customerDto) {
        Integer result = this.customerService.edit(customerDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
