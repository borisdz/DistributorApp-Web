package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import mk.ukim.finki.db.distributorapp.repository.CustomerRepository;
import mk.ukim.finki.db.distributorapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private List<CustomerDto> buildDto(List<Customer> customers) {
        List<CustomerDto> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto dto = new CustomerDto(
                    customer.getUserId(),
                    customer.getUsername(),
                    customer.getUserEmail(),
                    customer.getUserMobile(),
                    customer.getCustomerEDB(),
                    customer.getCustomerCompanyName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerOpenTime(),
                    customer.getCustomerCloseTime(),
                    customer.getCustomerRepresentativeImage()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        return buildDto(customers);
    }

    @Override
    public List<CustomerDto> findCustomerByName(String name) {
        List<Customer> customers = this.customerRepository.findAllByName(name);
        return buildDto(customers);
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id).get();
        return new CustomerDto(
                customer.getUserId(),
                customer.getUsername(),
                customer.getUserEmail(),
                customer.getUserMobile(),
                customer.getCustomerEDB(),
                customer.getCustomerCompanyName(),
                customer.getCustomerAddress(),
                customer.getCustomerOpenTime(),
                customer.getCustomerCloseTime(),
                customer.getCustomerRepresentativeImage()
        );
    }

    @Override
    public Integer create(CustomerDto customerDto) {
        return this.customerRepository.create(
                customerDto.getId(),
                customerDto.getEdb(),
                customerDto.getCompName(),
                customerDto.getAddress(),
                customerDto.getOpenTime(),
                customerDto.getCloseTime(),
                customerDto.getRepImage());
    }

    @Override
    public Integer edit(CustomerDto customerDto) {
        return this.customerRepository.edit(
                customerDto.getId(),
                customerDto.getEdb(),
                customerDto.getCompName(),
                customerDto.getAddress(),
                customerDto.getOpenTime(),
                customerDto.getCloseTime(),
                customerDto.getRepImage());
    }

    @Override
    public void delete(Long id) {
        this.customerRepository.delete(id);
    }
}
