package eu.senla.naumovich.config;

import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.repositories.UserRepository;
import eu.senla.naumovich.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.transaction.annotation.Transactional;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Autowired
    UserRepository repository;

    @Transactional
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        User user = repository.findByEmail(annotation.username());
        UserDetails securityUser = SecurityUser.fromUser(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(securityUser, "", securityUser.getAuthorities());
        securityContext.setAuthentication(auth);
        return securityContext;
    }
}
