package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.UserDTO;
import com.whereismyfood.restapi.domain.User;
import com.whereismyfood.restapi.enums.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public class UserMapperTest {
    UserMapper userMapper = UserMapper.INSTANCE;

    public static final String USER_LOGIN = "g.alex.andrade@gmail.com";
    public static final String USER_PASSWORD = "MySecretKey";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void fromUser() throws Exception {
        //Given
        User user = new User();
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);
        user.setRole(Role.ADMIN);

        //When
        UserDTO userDTO = userMapper.fromUser(user);

        //Then
        Assert.assertEquals(USER_LOGIN, userDTO.getLogin());
        Assert.assertEquals(USER_PASSWORD, userDTO.getPassword());
    }

    @Test
    public void toUser() throws Exception {
        //Given
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(USER_LOGIN);
        userDTO.setPassword(USER_PASSWORD);

        //When
        User user = userMapper.toUser(userDTO);

        //Then
        Assert.assertEquals(USER_LOGIN, user.getLogin());
        Assert.assertEquals(USER_PASSWORD, user.getPassword());
    }

}