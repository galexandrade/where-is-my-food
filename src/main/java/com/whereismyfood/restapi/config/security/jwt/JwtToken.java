package com.whereismyfood.restapi.config.security.jwt;

import com.whereismyfood.restapi.domain.User;
import com.whereismyfood.restapi.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alex P. Andrade on 10/06/2018.
 */
@Component
public class JwtToken {
    private final UserRepository users;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration_days}")
    private int expiration_days;

    public JwtToken(UserRepository users) {
        this.users = users;
    }

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseToken(String token) {
        User user = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = users.findById(Long.parseLong((String) body.get("userId"))).get();

        } catch (JwtException e) {
            // Simply print the exception and null will be returned for the userDto
            e.printStackTrace();
        }
        return user;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());
        claims.put("userId", user.getId() + "");
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date getExpirationDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, expiration_days);
        return c.getTime();
    }
}
