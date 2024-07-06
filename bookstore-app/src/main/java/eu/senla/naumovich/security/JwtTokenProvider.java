package eu.senla.naumovich.security;

import eu.senla.naumovich.services.service.auth.TokenBlackListService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final String secret = "secret";
    private final UserDetailsService service;
    private final TokenBlackListService tokenBlackListService;

    public String createToken(long id, String name, String email, String role, List<String> privileges) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("id", id);
        claims.put("username", name);
        claims.put("role", role);
        claims.put("privileges", privileges);
        Date now = new Date();
        int expirationTime = 30 * 60 * 1000;
        Date validity = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean isValidateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()) && !tokenBlackListService.isTokenInBlackList(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("JWT token is expired or invalid");
        }
    }

    @Transactional
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.service.loadUserByUsername(getEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
