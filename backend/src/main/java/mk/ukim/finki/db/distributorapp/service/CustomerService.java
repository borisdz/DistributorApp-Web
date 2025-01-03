package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAllCustomers();

    List<CustomerDto> findCustomerByName(String name);

    CustomerDto findCustomerById(Long id);

    Integer create(CustomerDto customerDto);

    Integer edit(CustomerDto customerDto);

    void deleteById(Long id);
}
