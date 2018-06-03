package com.whereismyfood.restapi.domain;

import com.whereismyfood.restapi.enums.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public class UserTest {
    User user;
    public static final Long USER_ID = 1L;
    public static final String USER_LOGIN = "user@user.com";
    public static final String USER_PASSWORD = "123456";
    public static final Role USER_ROLE = Role.ADMIN;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void getId() throws Exception {
        user.setId(USER_ID);
        Assert.assertEquals(USER_ID, user.getId());
    }

    @Test
    public void getLogin() throws Exception {
        user.setLogin(USER_LOGIN);
        Assert.assertEquals("Login is notEqual", USER_LOGIN, user.getLogin());
    }

    @Test
    public void getPassword() throws Exception {
        user.setLogin(USER_PASSWORD);
        Assert.assertEquals("Login is notEqual", USER_PASSWORD, user.getLogin());
    }

    @Test
    public void getRole() throws Exception {
        user.setRole(USER_ROLE);
        Assert.assertEquals(USER_ROLE, user.getRole());
    }

}