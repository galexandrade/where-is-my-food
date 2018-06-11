package com.whereismyfood.restapi.controllers;

import com.whereismyfood.restapi.exceptions.InvalidUserException;
import com.whereismyfood.restapi.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Alex P. Andrade on 06/06/2018.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidUserException.class})
    public ResponseEntity<Object> handleInvalidUserException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Invalid User", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
