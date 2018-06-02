package com.whereismyfood.restapi.repositories;

import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.Product;
import com.whereismyfood.restapi.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    public static final String CUSTOMER_FIRST_NAME = "Alex";
    public static final String CUSTOMER_LAST_NAME = "Andrade";

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveTest(){
        long beforeCount = customerRepository.count();

        Customer customer = CustomerRepositoryTest.createObject();

        customerRepository.save(customer);

        Assert.assertEquals(beforeCount, customerRepository.count() - 1);
    }

    @Test
    public void updateTest(){
        Customer customer = CustomerRepositoryTest.createObject();

        customerRepository.save(customer);

        customer.setFirstName(CUSTOMER_FIRST_NAME + "NEW");

        Customer storedCustomer = customerRepository.save(customer);

        Assert.assertEquals("Must have the password updated", storedCustomer.getFirstName(), CUSTOMER_FIRST_NAME + "NEW");
    }

    @Test
    public void deleteTest(){
        long beforeCount = customerRepository.count();

        Customer customer = CustomerRepositoryTest.createObject();

        customerRepository.save(customer);

        customerRepository.delete(customer);

        Assert.assertEquals(beforeCount, customerRepository.count());
    }

    public static Customer createObject(){
        Customer data = new Customer();
        data.setFirstName(CUSTOMER_FIRST_NAME);
        data.setLastName(CUSTOMER_LAST_NAME);
        data.setUser(UserRepositoryTest.createObject());

        return data;
    }
}