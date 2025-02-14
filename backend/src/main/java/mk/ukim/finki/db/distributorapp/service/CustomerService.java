package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CustomerDto;

public interface CustomerService {

    CustomerDto findCustomerById(Long id);

    Integer create(CustomerDto customerDto);

    Integer edit(CustomerDto customerDto);

    void deleteById(Long id);
}
