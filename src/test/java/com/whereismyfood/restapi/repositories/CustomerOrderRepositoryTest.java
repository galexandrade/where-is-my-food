package com.whereismyfood.restapi.repositories;

import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.domain.OrderItem;
import com.whereismyfood.restapi.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerOrderRepositoryTest {
    public static final int ORDER_ITEM_AMOUNT = 3;

    public static final String PRODUCT_TITLE = "BBQ Beef Brisket Sandwiches";
    public static final String PRODUCT_DESCRIPTION = "Slow-cooked seasoned brisket shredded and prepared for tantalizing barbeque sandwiches. Enjoy on your favorite bread.";
    public static final String PRODUCT_IMAGE_URL = "https://images.media-allrecipes.com/userphotos/560x315/4415106.jpg";
    public static final Float PRODUCT_PRICE = 10.5F;

    public static final String CUSTOMER_FIRST_NAME = "Alex";
    public static final String CUSTOMER_LAST_NAME = "Andrade";

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveTest(){
        long beforeCount = customerOrderRepository.count();

        CustomerOrder customerOrder = CustomerOrderRepositoryTest.createObject(
                this.createCustomer(),
                this.createProduct()
        );

        customerOrderRepository.save(customerOrder);

        Assert.assertEquals(beforeCount, customerOrderRepository.count() - 1);
    }

    @Test
    @Ignore
    public void updateTest(){
        CustomerOrder customerOrder = CustomerOrderRepositoryTest.createObject(
                this.createCustomer(),
                this.createProduct()
        );

        customerOrderRepository.save(customerOrder);

        customerOrder.getItems().get(0).setAmount(1);

        CustomerOrder storedCustomerOrder = customerOrderRepository.save(customerOrder);

        Assert.assertEquals((long) storedCustomerOrder.getItems().get(0).getAmount(), 1L);
    }

    @Test
    public void deleteTest(){
        long beforeCount = customerOrderRepository.count();

        CustomerOrder customerOrder = CustomerOrderRepositoryTest.createObject(
                this.createCustomer(),
                this.createProduct()
        );

        customerOrderRepository.save(customerOrder);

        customerOrderRepository.delete(customerOrder);

        Assert.assertEquals(beforeCount, customerOrderRepository.count());
    }

    private Product createProduct(){
        Product data = new Product();
        data.setTitle(PRODUCT_TITLE);
        data.setDescription(PRODUCT_DESCRIPTION);
        data.setImageUrl(PRODUCT_IMAGE_URL);
        data.setPrice(PRODUCT_PRICE);

        return productRepository.save(data);
    }

    private Customer createCustomer(){
        Customer data = new Customer();
        data.setFirstName(CUSTOMER_FIRST_NAME);
        data.setLastName(CUSTOMER_LAST_NAME);
        data.setUser(UserRepositoryTest.createObject());

        return customerRepository.save(data);
    }

    public static CustomerOrder createObject(Customer storedCustomer, Product storedProduct){
        CustomerOrder data = new CustomerOrder();
        data.setCustomer(storedCustomer);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(storedProduct);
        orderItem.setAmount(ORDER_ITEM_AMOUNT);
        orderItem.setOrder(data);

        data.setItems(Arrays.asList(orderItem));

        return data;
    }
}