package mk.ukim.finki.db.distributorapp.customer;

import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerFullDto;

public interface CustomerService {

    CustomerDto findCustomerById(Long id);

    Integer create(CustomerDto customerDto);

    Integer edit(CustomerDto customerDto);

    void deleteById(Long id);

    CustomerFullDto getCustomerProfile(String userEmail);

    void updateCustomer(Long id, String edb, String compName, String repImage);

    void updateCustomerDetails(Long id, String edb, String compName);
}
