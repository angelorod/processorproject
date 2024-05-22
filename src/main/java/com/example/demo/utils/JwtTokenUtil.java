package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}") // Load your secret key from application properties
    private String jwtSecret;

    @Value("${jwt.expirationMs}") // Load expiration time in milliseconds
    private int jwtExpirationMs;

    @Value("${jwt.issuer}") // Load the issuer from application properties
    private String jwtIssuer;

    public String generateToken(String username, String phone) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        String subject = username + ":" + phone; // Or a more secure method

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(jwtIssuer)          // Set the issuer
                .setIssuedAt(now)            // Set the issue date
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            Claims claims = parseToken(authToken);

            // Additional validation checks:
            if (claims.getExpiration().before(new Date())) { // Token expired
                return false;
            }
            if (!claims.getIssuer().equals(jwtIssuer)) {     // Wrong issuer
                return false;
            }

            return true; // All checks passed

        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature"); 
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false; // Validation failed
    }
}