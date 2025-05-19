package mk.ukim.finki.db.distributorapp.customer;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerFullDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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

    @Override
    public CustomerFullDto getCustomerProfile(String userEmail) {
        return this.customerRepository.getCustomerProfile(userEmail);
    }

    @Override
    @Transactional
    public void updateCustomer(Long id, String edb, String compName, String repImage) {
        this.customerRepository.updateCustomer(
                id,
                edb,
                compName,
                repImage
        );
    }

    @Override
    @Transactional
    public void updateCustomerDetails(Long id, String edb, String compName) {
        this.customerRepository.updateCustomerDetails(
                id,
                edb,
                compName
        );
    }

}
