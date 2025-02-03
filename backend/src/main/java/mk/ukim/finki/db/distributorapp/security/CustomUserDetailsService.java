package mk.ukim.finki.db.distributorapp.security;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.repository.CustomerRepository;
import mk.ukim.finki.db.distributorapp.repository.DriverRepository;
import mk.ukim.finki.db.distributorapp.repository.ManagerRepository;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

        Users user = this.usersRepository.findUsersByUserEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        if(Objects.equals(user.getClazz_(), "CUSTOMER")){
            return this.customerRepository.findById(user.getUserId())
                    .orElseThrow(() -> new UsernameNotFoundException(email));
        }else if(Objects.equals(user.getClazz_(), "MANAGER")){
            return this.managerRepository.findById(user.getUserId())
                    .orElseThrow(() -> new UsernameNotFoundException(email));
        } else if(Objects.equals(user.getClazz_(), "DRIVER")){
            return this.driverRepository.findById(user.getUserId())
                    .orElseThrow(() -> new UsernameNotFoundException(email));
        }
        return user;
    }
}
