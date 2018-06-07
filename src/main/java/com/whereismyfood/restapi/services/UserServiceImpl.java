package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.UserMapper;
import com.whereismyfood.restapi.api.v1.model.UserDTO;
import com.whereismyfood.restapi.domain.User;
import com.whereismyfood.restapi.exceptions.ResourceNotFoundException;
import com.whereismyfood.restapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Alex P. Andrade on 04/06/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(userMapper::fromUser)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return this.saveAndReturnDTO(user);
    }

    @Override
    public UserDTO saveUser(Long id, UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user.setId(id);

        return this.saveAndReturnDTO(user);
    }

    @Override
    public UserDTO patchUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {

            if(userDTO.getPassword() != null){
                user.setPassword(userDTO.getPassword());
            }

            if(userDTO.getLogin() != null){
                user.setLogin(userDTO.getLogin());
            }

            return userMapper.fromUser(userRepository.save(user));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO saveAndReturnDTO(User user) {
        User savedUser = userRepository.save(user);

        UserDTO returnDTO = userMapper.fromUser(savedUser);

        return returnDTO;
    }
}
