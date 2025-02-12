package mk.ukim.finki.db.distributorapp.security;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.Users;
import mk.ukim.finki.db.distributorapp.customer.CustomerRepository;
import mk.ukim.finki.db.distributorapp.driver.DriverRepository;
import mk.ukim.finki.db.distributorapp.manager.ManagerRepository;
import mk.ukim.finki.db.distributorapp.users.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final DriverRepository driverRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return new Users(this.usersRepository.findUsersByUserEmailIgnoreCaseDto(email));

//        if(Objects.equals(user.getClazz_(), "CUSTOMER")){
//            return this.customerRepository.findCustomerById(user.getUserId())
//                    .orElseThrow(() -> new UsernameNotFoundException(email));
//        }else if(Objects.equals(user.getClazz_(), "MANAGER")){
//            return this.managerRepository.findCustomerById(user.getUserId())
//                    .orElseThrow(() -> new UsernameNotFoundException(email));
//        } else if(Objects.equals(user.getClazz_(), "DRIVER")){
//            return this.driverRepository.findCustomerById(user.getUserId())
//                    .orElseThrow(() -> new UsernameNotFoundException(email));
//        }
//        return user;
    }
}
