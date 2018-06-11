package com.whereismyfood.restapi.controllers.v1;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.CustomerListDTO;
import com.whereismyfood.restapi.api.v1.model.UserDTO;
import com.whereismyfood.restapi.config.security.jwt.JwtToken;
import com.whereismyfood.restapi.domain.User;
import com.whereismyfood.restapi.services.CustomerService;
import com.whereismyfood.restapi.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alex P. Andrade on 06/06/2018.
 */
@RestController
@RequestMapping(PublicController.BASE_URL)
@CrossOrigin(origins = "*")
@Slf4j
@Api(description = "Public Controller")
public class PublicController {
    public static final String BASE_URL = "/api/v1/public";
    @Autowired
    private JwtToken jwtToken;

    private final UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "authentication")
    public ResponseEntity<?> auth(@RequestBody UserDTO credentials) throws Exception {
        java.lang.String message = "Not authorized!";
        log.info("Credentials: " + credentials);

        /*TO DO: Verify user credentials here!*/
        User user = userService.getAuthenticatedUser(credentials);

        return ResponseEntity.ok(jwtToken.generateToken(user));
    }

}
