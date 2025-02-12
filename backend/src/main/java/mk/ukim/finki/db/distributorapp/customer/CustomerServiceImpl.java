package mk.ukim.finki.db.distributorapp.customer;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

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
