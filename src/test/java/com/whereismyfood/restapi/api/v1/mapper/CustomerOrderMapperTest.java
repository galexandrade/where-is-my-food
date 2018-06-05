package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.CustomerOrderDTO;
import com.whereismyfood.restapi.api.v1.model.OrderItemDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.domain.OrderItem;
import com.whereismyfood.restapi.repositories.CustomerRepositoryTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Alex P. Andrade on 04/06/2018.
 */
public class CustomerOrderMapperTest {
    public static final String CUSTOMER_FIRST_NAME = "Alex";
    public static final String CUSTOMER_LAST_NAME = "Andrade";

    CustomerOrderMapper customerOrderMapper = CustomerOrderMapper.INSTANCE;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void fromCustomerOrder() throws Exception {
        //Given
        Customer customer = CustomerRepositoryTest.createObject();

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setItems(Arrays.asList(new OrderItem(), new OrderItem()));

        //When
        CustomerOrderDTO customerOrderDTO = customerOrderMapper.fromCustomerOrder(customerOrder);

        //When
        Assert.assertEquals(customer.getFirstName(), customerOrderDTO.getCustomer().getFirstName());
        Assert.assertEquals(2, customerOrderDTO.getItems().size());
    }

    @Test
    public void toCustomerOrder() throws Exception {
        //Given
        CustomerDTO customerDTO = customerMapper.fromCustomer(CustomerRepositoryTest.createObject());

        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();
        customerOrderDTO.setCustomer(customerDTO);
        customerOrderDTO.setItems(Arrays.asList(new OrderItemDTO(), new OrderItemDTO()));

        //When
        CustomerOrder customerOrder = customerOrderMapper.toCustomerOrder(customerOrderDTO);

        //When
        Assert.assertEquals(customerDTO.getFirstName(), customerOrder.getCustomer().getFirstName());
        Assert.assertEquals(2, customerOrder.getItems().size());
    }

}