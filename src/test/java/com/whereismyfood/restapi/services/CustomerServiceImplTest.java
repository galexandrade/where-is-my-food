package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.CustomerMapper;
import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.repositories.CustomerRepository;
import com.whereismyfood.restapi.repositories.CustomerRepositoryTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex P. Andrade on 04/06/2018.
 */
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        //Given
        Customer customer1 = CustomerRepositoryTest.createObject();
        Customer customer2 = CustomerRepositoryTest.createObject();

        //When
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        List<CustomerDTO> customers = customerService.getAllCustomers();

        //Then
        Assert.assertEquals(2, customers.size());
    }

    @Test
    public void getCustomerById() throws Exception {
        //Given
        Customer customer = CustomerRepositoryTest.createObject();

        //When
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        //Then
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
    }

    @Test
    public void createNewCustomer() throws Exception {
        //Given
        Customer savedCustomer = CustomerRepositoryTest.createObject();
        CustomerDTO customerDTO = customerMapper.fromCustomer(savedCustomer);

        //When
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        CustomerDTO savedCustomerDTO = customerService.createNewCustomer(customerDTO);

        //Then
        Assert.assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getFirstName());
    }

    @Test
    public void saveCustomer() throws Exception {
        //Given
        Customer savedCustomer = CustomerRepositoryTest.createObject();
        savedCustomer.setId(1L);

        CustomerDTO customerDTO = customerMapper.fromCustomer(savedCustomer);

        //When
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        CustomerDTO savedCustomerDTO = customerService.saveCustomer(1L, customerDTO);

        //Then
        Assert.assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getFirstName());
    }

    @Test
    public void patchCustomer() throws Exception {
        //Given
        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("BBQ Beef Brisket Sandwiches");

        //When
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(savedCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);

        CustomerDTO savedCustomerDTO = customerService.patchCustomer(1L, customerDTO);

        //Then
        verify(customerRepository, times(1)).save(argumentCaptor.capture());
        Customer captureCustomer = argumentCaptor.getValue();

        Assert.assertNull(captureCustomer.getLastName());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        //Given
        Long idToDelete = 1L;

        //When
        customerService.deleteCustomerById(idToDelete);

        //Then
        verify(customerRepository, times(1)).deleteById(anyLong());

    }

}