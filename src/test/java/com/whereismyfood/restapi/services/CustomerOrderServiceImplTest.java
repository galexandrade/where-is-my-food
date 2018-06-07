package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.CustomerMapper;
import com.whereismyfood.restapi.api.v1.mapper.CustomerOrderMapper;
import com.whereismyfood.restapi.api.v1.mapper.ProductMapper;
import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.CustomerOrderDTO;
import com.whereismyfood.restapi.api.v1.model.OrderItemDTO;
import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.domain.Product;
import com.whereismyfood.restapi.repositories.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex P. Andrade on 06/06/2018.
 */
public class CustomerOrderServiceImplTest {
    @Mock
    private CustomerOrderRepository customerOrderRepository;
    @Mock
    private CustomerRepository customerRepository;

    private CustomerOrderMapper customerOrderMapper = CustomerOrderMapper.INSTANCE;
    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    private ProductMapper productMapper = ProductMapper.INSTANCE;

    private CustomerOrderService customerOrderService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerOrderService = new CustomerOrderServiceImpl(customerOrderRepository, customerRepository);
    }

    @Test
    public void getAllCustomerOrders() throws Exception {
        //Given
        Customer customer = CustomerRepositoryTest.createObject();
        Product product = ProductRepositoryTest.createObject();

        CustomerOrder order1 = CustomerOrderRepositoryTest.createObject(customer, product);
        CustomerOrder order2 = CustomerOrderRepositoryTest.createObject(customer, product);

        //When
        when(customerOrderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        List<CustomerOrderDTO> orders = customerOrderService.getAllCustomerOrders();

        //Then
        Assert.assertEquals(2, orders.size());
    }

    @Test
    public void getCustomerOrders() throws Exception {
        //Given
        Customer customer = CustomerRepositoryTest.createObject();
        customer.setId(1L);
        Product product = ProductRepositoryTest.createObject();

        CustomerOrder order1 = CustomerOrderRepositoryTest.createObject(customer, product);
        CustomerOrder order2 = CustomerOrderRepositoryTest.createObject(customer, product);

        //When
        when(customerOrderRepository.findByCustomerId(anyLong())).thenReturn(Arrays.asList(order1, order2));
        List<CustomerOrderDTO> orders = customerOrderService.getCustomerOrders(1L);

        //Then
        Assert.assertEquals(2, orders.size());
    }

    @Test
    public void getCustomerOrderById() throws Exception {
        //Given
        Customer customer = CustomerRepositoryTest.createObject();
        Product product = ProductRepositoryTest.createObject();

        CustomerOrder order1 = CustomerOrderRepositoryTest.createObject(customer, product);

        //When
        when(customerOrderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order1));
        CustomerOrderDTO order = customerOrderService.getCustomerOrderById(1L);

        //Then
        Assert.assertEquals(order1.getCustomer().getFirstName(), order.getCustomer().getFirstName());
    }

    @Test
    public void createNewCustomerOrder() throws Exception {
        //Given
        Customer customer = CustomerRepositoryTest.createObject();
        customer.setId(1L);
        Product product = ProductRepositoryTest.createObject();

        CustomerOrder storedOrder = CustomerOrderRepositoryTest.createObject(customer, product);

        CustomerDTO customerDTO = customerMapper.fromCustomer(customer);
        ProductDTO productDTO = productMapper.fromProduct(product);

        CustomerOrderDTO order = new CustomerOrderDTO();
        order.setCustomer(customerDTO);

        OrderItemDTO item1 = new OrderItemDTO();
        item1.setProduct(productDTO);
        item1.setAmount(2);

        order.setItems(Arrays.asList(item1));

        //When
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerOrderRepository.save(any(CustomerOrder.class))).thenReturn(storedOrder);
        CustomerOrderDTO savedCustomerDTO = customerOrderService.createNewCustomerOrder(1L, order);

        //Then
        Assert.assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getCustomer().getFirstName());
    }

    @Test
    public void saveCustomerOrder() throws Exception {
        //Given
        Customer customer = CustomerRepositoryTest.createObject();
        Product product = ProductRepositoryTest.createObject();

        CustomerOrder storedOrder = CustomerOrderRepositoryTest.createObject(customer, product);

        CustomerDTO customerDTO = customerMapper.fromCustomer(customer);
        ProductDTO productDTO = productMapper.fromProduct(product);

        CustomerOrderDTO order = new CustomerOrderDTO();
        order.setCustomer(customerDTO);

        OrderItemDTO item1 = new OrderItemDTO();
        item1.setProduct(productDTO);
        item1.setAmount(2);

        order.setItems(Arrays.asList(item1));

        //When
        when(customerOrderRepository.save(any(CustomerOrder.class))).thenReturn(storedOrder);
        CustomerOrderDTO savedCustomerDTO = customerOrderService.saveCustomerOrder(1L, order);

        //Then
        Assert.assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getCustomer().getFirstName());
    }

    @Test
    public void deleteCustomerOrderById() throws Exception {
        //Given
        Long idToDelete = 1L;

        //When
        customerOrderService.deleteCustomerOrderById(idToDelete);

        //Then
        verify(customerOrderRepository, times(1)).deleteById(anyLong());
    }

}