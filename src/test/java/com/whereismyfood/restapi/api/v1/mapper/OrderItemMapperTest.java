package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.OrderItemDTO;
import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.domain.OrderItem;
import com.whereismyfood.restapi.domain.Product;
import com.whereismyfood.restapi.repositories.ProductRepositoryTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 04/06/2018.
 */
public class OrderItemMapperTest {
    OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;
    ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    public void fromOrderItem() throws Exception {
        //Given
        CustomerOrder order = new CustomerOrder();
        order.setId(1L);

        Product product = ProductRepositoryTest.createObject();

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setAmount(10);

        //When
        OrderItemDTO orderItemDTO = orderItemMapper.fromOrderItem(orderItem);

        //Then
        Assert.assertEquals(product.getTitle(), orderItem.getProduct().getTitle());
        Assert.assertEquals(Integer.valueOf(10), orderItem.getAmount());
    }

    @Test
    public void toOrderItem() throws Exception {
        //Given
        ProductDTO product = productMapper.fromProduct(ProductRepositoryTest.createObject());
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setAmount(10);
        orderItemDTO.setProduct(product);

        //When
        OrderItem orderItem = orderItemMapper.toOrderItem(orderItemDTO);

        Assert.assertEquals(product.getTitle(), orderItem.getProduct().getTitle());
        Assert.assertEquals(orderItemDTO.getAmount(), orderItem.getAmount());
    }

}