package com.whereismyfood.restapi.controllers.v1;

import com.whereismyfood.restapi.api.v1.mapper.CustomerMapper;
import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.controllers.RestResponseEntityExceptionHandler;
import com.whereismyfood.restapi.repositories.CustomerRepositoryTest;
import com.whereismyfood.restapi.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.whereismyfood.restapi.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Alex P. Andrade on 08/06/2018.
 */
public class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        //Given
        CustomerDTO customer1 = customerMapper.fromCustomer(CustomerRepositoryTest.createObject());
        CustomerDTO customer2 = customerMapper.fromCustomer(CustomerRepositoryTest.createObject());

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        //When
        when(customerService.getAllCustomers()).thenReturn(customers);

        //Then
        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        //Given
        CustomerDTO customer = customerMapper.fromCustomer(CustomerRepositoryTest.createObject());

        //When
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        //Then
        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())));
    }

    @Test
    public void  createNewCustomer() throws Exception{
        //Given
        CustomerDTO customer = customerMapper.fromCustomer(CustomerRepositoryTest.createObject());

        //When
        when(customerService.createNewCustomer(customer)).thenReturn(customer);

        //Then
        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())));
    }

    @Test
    public void  saveCustomer() throws Exception{
        //Given
        CustomerDTO customer = customerMapper.fromCustomer(CustomerRepositoryTest.createObject());

        //When
        when(customerService.saveCustomer(anyLong(), ArgumentMatchers.any(CustomerDTO.class))).thenReturn(customer);

        //Then
        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())));
    }

    @Test
    public void  patchCustomer() throws Exception{
        //Given
        CustomerDTO customer = customerMapper.fromCustomer(CustomerRepositoryTest.createObject());

        //When
        when(customerService.patchCustomer(anyLong(), ArgumentMatchers.any(CustomerDTO.class))).thenReturn(customer);

        //Then
        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())));
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}