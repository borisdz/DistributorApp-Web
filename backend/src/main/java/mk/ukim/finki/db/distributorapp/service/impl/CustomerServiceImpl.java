package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.repository.CustomerRepository;
import mk.ukim.finki.db.distributorapp.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto findCustomerById(Long id) {
        return this.customerRepository.findCustomerById(id);
    }

    @Override
    public Integer create(CustomerDto customerDto) {
        return this.customerRepository.create(
                customerDto.getId(),
                customerDto.getEdb(),
                customerDto.getCompName(),
                customerDto.getAddress(),
                customerDto.getRepImage());
    }

    @Override
    public Integer edit(CustomerDto customerDto) {
        return this.customerRepository.edit(
                customerDto.getId(),
                customerDto.getEdb(),
                customerDto.getCompName(),
                customerDto.getAddress(),
                customerDto.getRepImage());
    }

    @Override
    public void deleteById(Long id) {
        this.customerRepository.delete(id);
    }
}
