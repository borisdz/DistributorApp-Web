package mk.ukim.finki.db.distributorapp._security;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.User;
import mk.ukim.finki.db.distributorapp.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new User(this.userRepository.findUsersByUserEmailIgnoreCaseDto(email));
    }
}
