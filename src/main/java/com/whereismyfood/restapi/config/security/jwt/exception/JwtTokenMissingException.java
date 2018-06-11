package com.whereismyfood.restapi.config.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown when token cannot be found in the request header
 * Created by Alex P. Andrade on 10/06/2018.
 */

public class JwtTokenMissingException extends AuthenticationException {


    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}