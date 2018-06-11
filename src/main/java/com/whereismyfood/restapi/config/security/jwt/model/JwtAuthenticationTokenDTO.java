package com.whereismyfood.restapi.config.security.jwt.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by Alex P. Andrade on 10/06/2018.
 */
public class JwtAuthenticationTokenDTO extends UsernamePasswordAuthenticationToken {
    private String token;

    public JwtAuthenticationTokenDTO(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
