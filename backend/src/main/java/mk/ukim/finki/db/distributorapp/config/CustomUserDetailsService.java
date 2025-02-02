package mk.ukim.finki.db.distributorapp.config;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PassEncryptionPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.usersRepository.findUsersByUserEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean authenticateUser(String providedPassword, Users user) {
        return passwordEncoder.matchesWithSalt(providedPassword, user.getPassword(), user.getUserSalt());
    }
}
