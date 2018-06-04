package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.model.UserDTO;

import java.util.List;

/**
 * Created by Alex P. Andrade on 03/06/2018.
 */
public interface UserService {

    UserDTO getUserById(Long id);

    UserDTO createNewUser(UserDTO userDTO);

    UserDTO saveUser(Long id, UserDTO userDTO);

    UserDTO patchUser(Long id, UserDTO userDTO);

    void deleteUserById(Long id);
}
