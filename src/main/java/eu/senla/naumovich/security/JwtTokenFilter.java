package eu.senla.naumovich.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
          try {
              if (token != null && jwtTokenProvider.isValidateToken(token)) {
                  Authentication authentication = jwtTokenProvider.getAuthentication(token);
                  if (authentication != null) {
                      SecurityContextHolder.getContext().setAuthentication(authentication);
                  }
              }
          }catch (JwtException e){
              log.error("JWT token is expired or invalid");
              SecurityContextHolder.clearContext();
          }
        chain.doFilter(request, response);
    }
}
