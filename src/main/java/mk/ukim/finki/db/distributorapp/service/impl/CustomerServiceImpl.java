package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Customer;
import mk.ukim.finki.db.distributorapp.repository.CustomerRepository;
import mk.ukim.finki.db.distributorapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomerByName(String name) {
        return this.customerRepository.findAllByName(name);
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> create(Long id, String customerEDB, String customerName, String customerStreet, LocalTime openTime, LocalTime closeTime, String customerImage) {
        return this.customerRepository.create(id, customerEDB, customerName, customerStreet, openTime, closeTime, customerImage);
    }

    @Override
    public Optional<Customer> edit(Long id, String customerEDB, String customerName, String customerStreet, LocalTime openTime, LocalTime closeTime, String customerImage) {
        return this.customerRepository.edit(id, customerEDB, customerName, customerStreet, openTime, closeTime, customerImage);
    }

    @Override
    public void delete(Long id) {
        this.customerRepository.delete(id);
    }
}
