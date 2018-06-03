package com.whereismyfood.restapi.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public class OrderItemTest {
    OrderItem orderItem;

    public static final Long ORDER_ITEM_ID = 1L;
    public static final Integer ORDER_ITEM_AMOUNT = 10;
    public static final Long CUSTOMER_ORDER_ITEM_ID = 2L;
    public static final String PRODUCT_TITLE = "BBQ Beef Brisket Sandwiches";

    @Before
    public void setUp() throws Exception {
        orderItem = new OrderItem();
    }

    @Test
    public void getId() throws Exception {
        orderItem.setId(ORDER_ITEM_ID);
        Assert.assertEquals(ORDER_ITEM_ID, orderItem.getId());
    }

    @Test
    public void getOrder() throws Exception {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(CUSTOMER_ORDER_ITEM_ID);
        orderItem.setOrder(customerOrder);

        Assert.assertEquals(CUSTOMER_ORDER_ITEM_ID, orderItem.getOrder().getId());
    }

    @Test
    public void getProduct() throws Exception {
        Product product = new Product();
        product.setTitle(PRODUCT_TITLE);
        orderItem.setProduct(product);

        Assert.assertEquals(PRODUCT_TITLE, orderItem.getProduct().getTitle());
    }

    @Test
    public void getAmount() throws Exception {
        orderItem.setAmount(ORDER_ITEM_AMOUNT);
        Assert.assertEquals(ORDER_ITEM_AMOUNT, orderItem.getAmount());
    }

}