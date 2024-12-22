package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Customer;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAllCustomers();

    List<Customer> findCustomerByName(String name);

    Optional<Customer> findCustomerById(Long id);

    Optional<Customer> create(Long id, String customerEDB, String customerName,
                              String customerStreet, LocalTime openTime, LocalTime closeTime, String customerImage);

    Optional<Customer> edit (Long id, String customerEDB, String customerName,
                             String customerStreet, LocalTime openTime, LocalTime closeTime, String customerImage);
    void delete (Long id);
}
