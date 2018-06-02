package com.whereismyfood.restapi.bootstrap;

import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.domain.OrderItem;
import com.whereismyfood.restapi.domain.User;
import com.whereismyfood.restapi.enums.Role;
import com.whereismyfood.restapi.repositories.CustomerOrderRepository;
import com.whereismyfood.restapi.repositories.CustomerRepository;
import com.whereismyfood.restapi.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Slf4j
@Component
@Profile("dev")
public class Bootstrap  implements ApplicationListener<ContextRefreshedEvent> {
    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final ProductRepository productRepository;

    public Bootstrap(CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Bootstrap data");
        this.createCustomerData();
    }

    private void createCustomerData(){
        User user = new User();
        user.setLogin("g.alex.andrade@gmail.com");
        user.setPassword("MySecretKey");
        user.setRole(Role.CUSTOMER);

        Customer customer = new Customer();
        customer.setFirstName("Alex");
        customer.setLastName("Andrade");
        customer.setUser(user);

        this.customerRepository.save(customer);

        /* Create order for the customer */
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);

        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(10);
        orderItem.setProduct(productRepository.getOne(1L));
        orderItem.setOrder(customerOrder);

        customerOrder.getItems().add(orderItem);

        this.customerOrderRepository.save(customerOrder);
    }
}
