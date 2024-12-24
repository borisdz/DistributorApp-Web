package mk.ukim.finki.db.distributorapp.config;

import mk.ukim.finki.db.distributorapp.model.Users;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(username).get();

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
