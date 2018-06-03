package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.domain.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public class CustomerMapperTest {
    public static final String CUSTOMER_FIRST_NAME = "Alex";
    public static final String CUSTOMER_LAST_NAME = "Andrade";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        //Given
        Customer customer = new Customer();
        customer.setFirstName(CUSTOMER_FIRST_NAME);
        customer.setLastName(CUSTOMER_LAST_NAME);

        //When
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //When
        Assert.assertEquals(CUSTOMER_FIRST_NAME, customerDTO.getFirstName());
        Assert.assertEquals(CUSTOMER_LAST_NAME, customerDTO.getLastName());
    }

    @Test
    public void customerDTOToCustomer() throws Exception {
        //Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(CUSTOMER_FIRST_NAME);
        customerDTO.setLastName(CUSTOMER_LAST_NAME);

        //When
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        //When
        Assert.assertEquals(CUSTOMER_FIRST_NAME, customer.getFirstName());
        Assert.assertEquals(CUSTOMER_LAST_NAME, customer.getLastName());
    }

}