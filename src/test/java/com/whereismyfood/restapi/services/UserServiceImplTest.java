package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.UserMapper;
import com.whereismyfood.restapi.api.v1.model.UserDTO;
import com.whereismyfood.restapi.domain.User;
import com.whereismyfood.restapi.repositories.UserRepository;
import com.whereismyfood.restapi.repositories.UserRepositoryTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex P. Andrade on 04/06/2018.
 */
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private UserMapper userMapper = UserMapper.INSTANCE;

    private UserService userService;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImpl(userMapper, userRepository);
    }

    @Test
    public void getUserById() throws Exception {
        //Given
        User user = UserRepositoryTest.createObject();

        //When
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(user));
        UserDTO userDTO = userService.getUserById(1L);

        //Then
        assertEquals(user.getLogin(), userDTO.getLogin());
    }

    @Test
    public void createNewUser() throws Exception {
        //Given
        User savedUser = UserRepositoryTest.createObject();
        UserDTO userDTO = userMapper.fromUser(savedUser);

        //When
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        UserDTO savedUserDTO = userService.createNewUser(userDTO);

        //Then
        Assert.assertEquals(userDTO.getLogin(), savedUserDTO.getLogin());
    }

    @Test
    public void saveUser() throws Exception {
        //Given
        User savedUser = UserRepositoryTest.createObject();
        savedUser.setId(1L);

        UserDTO userDTO = userMapper.fromUser(savedUser);

        //When
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        UserDTO savedUserDTO = userService.saveUser(1L, userDTO);

        //Then
        Assert.assertEquals(userDTO.getLogin(), savedUserDTO.getLogin());
    }

    @Test
    public void patchUser() throws Exception {
        //Given
        User savedUser = new User();
        savedUser.setId(1L);

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("user.test@test.com");

        //When
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(savedUser));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        UserDTO savedUserDTO = userService.patchUser(1L, userDTO);

        //Then
        verify(userRepository, times(1)).save(argumentCaptor.capture());
        User captureUser = argumentCaptor.getValue();

        Assert.assertNull(captureUser.getPassword());
    }

    @Test
    public void deleteUserById() throws Exception {
        //Given
        Long idToDelete = 1L;

        //When
        userService.deleteUserById(idToDelete);

        //Then
        verify(userRepository, times(1)).deleteById(anyLong());
    }

}