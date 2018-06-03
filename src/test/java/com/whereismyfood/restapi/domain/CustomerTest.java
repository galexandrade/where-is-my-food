package com.whereismyfood.restapi.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public class CustomerTest {
    Customer customer;

    public static final Long CUSTOMER_ID = 1L;
    public static final String CUSTOMER_FIRST_NAME = "Alex";
    public static final String CUSTOMER_LAST_NAME = "Andrade";
    public static final String USER_LOGIN = "g.alex.andrade@gmail.com";

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
    }

    @Test
    public void getId() throws Exception {
        customer.setId(CUSTOMER_ID);
        Assert.assertEquals(CUSTOMER_ID, customer.getId());
    }

    @Test
    public void getFirstName() throws Exception {
        customer.setFirstName(CUSTOMER_FIRST_NAME);
        Assert.assertEquals(CUSTOMER_FIRST_NAME, customer.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        customer.setLastName(CUSTOMER_LAST_NAME);
        Assert.assertEquals(CUSTOMER_LAST_NAME, customer.getLastName());
    }

    @Test
    public void getUser() throws Exception {
        User user = new User();
        user.setLogin(USER_LOGIN);
        customer.setUser(user);

        Assert.assertEquals(USER_LOGIN, user.getLogin());
    }

}