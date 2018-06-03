package com.whereismyfood.restapi.domain;

import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.domain.OrderItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public class CustomerOrderTest {
    CustomerOrder customerOrder;

    public static final Long CUSTOMER_ORDER_ID = 1L;
    public static final String CUSTOMER_FIRST_NAME = "Alex";

    @Before
    public void setUp() throws Exception {
        customerOrder = new CustomerOrder();
    }

    @Test
    public void getId() throws Exception {
        customerOrder.setId(CUSTOMER_ORDER_ID);
        Assert.assertEquals(CUSTOMER_ORDER_ID, customerOrder.getId());
    }

    @Test
    public void getItems() throws Exception {
        customerOrder.setItems(Arrays.asList(new OrderItem(), new OrderItem()));
        Assert.assertEquals(2, customerOrder.getItems().size());
    }

    @Test
    public void getCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName(CUSTOMER_FIRST_NAME);
        customerOrder.setCustomer(customer);

        Assert.assertEquals(CUSTOMER_FIRST_NAME, customerOrder.getCustomer().getFirstName());
    }

}