package eu.senla.naumovich.services.impl.auth;

import eu.senla.naumovich.repositories.UserRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.exceptions.NoRecordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = Optional.ofNullable(repository.findByEmail(email))
                .orElseThrow(() -> new NoRecordException("No user with email " + email));
        return SecurityUser.fromUser(user);
    }
}
