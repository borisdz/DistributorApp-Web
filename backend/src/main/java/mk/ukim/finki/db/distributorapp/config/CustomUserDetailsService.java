package mk.ukim.finki.db.distributorapp.config;

import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PassEncryptionPasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UsersRepository usersRepository, PassEncryptionPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(
                user.getUserEmail(),
                user.getUserPassword(),
                user.getAuthorities()
        );
    }

    public boolean authenticateUser(String providedPassword, Users user) {
        return passwordEncoder.matchesWithSalt(providedPassword, user.getPassword(), user.getUserSalt());
    }
}
