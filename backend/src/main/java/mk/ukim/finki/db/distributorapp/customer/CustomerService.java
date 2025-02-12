package mk.ukim.finki.db.distributorapp.customer;

import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAllCustomers();

    CustomerDto findCustomerById(Long id);

    Integer create(CustomerDto customerDto);

    Integer edit(CustomerDto customerDto);

    void deleteById(Long id);
}
