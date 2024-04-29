package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.UserRepository;

import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.exceptions.NoRecords;
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
        var user = Optional.ofNullable(repository.getUserByEmail(email))
                .orElseThrow(() -> new NoRecords("No user with email " + email));
        return SecurityUser.fromUser(user);
    }
}
