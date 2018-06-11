package com.whereismyfood.restapi.config.security.jwt;

import com.whereismyfood.restapi.config.security.jwt.exception.JwtTokenMalformedException;
import com.whereismyfood.restapi.config.security.jwt.model.AuthenticatedUserDTO;
import com.whereismyfood.restapi.config.security.jwt.model.JwtAuthenticationTokenDTO;
import com.whereismyfood.restapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alex P. Andrade on 10/06/2018.
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private JwtToken jwtToken;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationTokenDTO.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationTokenDTO jwtAuthenticationToken = (JwtAuthenticationTokenDTO) authentication;
        String token = jwtAuthenticationToken.getToken();

        User user = jwtToken.parseToken(token);

        if (user == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(new String[]{user.getRole().toString()});

        return new AuthenticatedUserDTO(user.getId(), user.getLogin(), token, authorityList);
    }

}
