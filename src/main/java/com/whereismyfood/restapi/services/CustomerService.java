package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by Alex P. Andrade on 03/06/2018.
 */
public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
