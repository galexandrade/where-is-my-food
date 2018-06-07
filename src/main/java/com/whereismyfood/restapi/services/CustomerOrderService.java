package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.CustomerOrderDTO;

import java.util.List;

/**
 * Created by Alex P. Andrade on 03/06/2018.
 */
public interface CustomerOrderService {
    List<CustomerOrderDTO> getAllCustomerOrders();

    List<CustomerOrderDTO> getCustomerOrders(Long customerId);

    CustomerOrderDTO getCustomerOrderById(Long id);

    CustomerOrderDTO createNewCustomerOrder(Long idCustomer, CustomerOrderDTO customerOrderDTO);

    CustomerOrderDTO saveCustomerOrder(Long id, CustomerOrderDTO customerOrderDTO);

    void deleteCustomerOrderById(Long id);
}
