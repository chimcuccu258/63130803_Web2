package org.ngavm1.deliverysystem.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expirationMs}")
    private int jwtExpirationMs;


    public String generateJwtToken(Authentication authentication) {

        org.springframework.security.core.userdetails.User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        String str = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
        return str;
    }

    // Get expiration date from token
    private Date getExpirationDateFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getExpiration();
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return getExpirationDateFromJwtToken(token).before(new Date());
    }

    public boolean validateJwtToken(String authToken) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, IllegalArgumentException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parse(authToken);


            // Check if token is expired
            return !isTokenExpired(authToken);

        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "JWT token is expired");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty");
        }

    }
}