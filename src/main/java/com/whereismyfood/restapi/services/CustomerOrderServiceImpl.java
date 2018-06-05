package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.CustomerOrderMapper;
import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.CustomerOrderDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.exceptions.ResourceNotFoundException;
import com.whereismyfood.restapi.repositories.CustomerOrderRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex P. Andrade on 04/06/2018.
 */
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderMapper customerOrderMapper;
    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderServiceImpl(CustomerOrderMapper customerOrderMapper, CustomerOrderRepository customerOrderRepository) {
        this.customerOrderMapper = customerOrderMapper;
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public List<CustomerOrderDTO> getAllCustomerOrders() {
        return customerOrderRepository
                .findAll()
                .stream()
                .map(customerOrder -> {
                    CustomerOrderDTO customerOrderDTO = customerOrderMapper.fromCustomerOrder(customerOrder);
                    return customerOrderDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerOrderDTO> getCustomerOrders(Long customerId) {
        return customerOrderRepository
                .findByCustomerId(customerId)
                .stream()
                .map(customerOrder -> {
                    CustomerOrderDTO customerOrderDTO = customerOrderMapper.fromCustomerOrder(customerOrder);
                    return customerOrderDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerOrderDTO getCustomerOrderById(Long id) {
        return customerOrderRepository
                .findById(id)
                .map(customerOrderMapper::fromCustomerOrder)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerOrderDTO createNewCustomerOrder(CustomerOrderDTO customerOrderDTO) {
        CustomerOrder customerOrder = customerOrderMapper.toCustomerOrder(customerOrderDTO);
        return this.saveAndReturnDTO(customerOrder);
    }

    @Override
    public CustomerOrderDTO saveCustomer(Long id, CustomerOrderDTO customerOrderDTO) {
        CustomerOrder customerOrder = customerOrderMapper.toCustomerOrder(customerOrderDTO);
        customerOrder.setId(id);

        return this.saveAndReturnDTO(customerOrder);
    }

    @Override
    public CustomerDTO patchCustomerOrder(Long id, CustomerOrderDTO customerOrderDTO) {
        return null;
    }

    @Override
    public void deleteCustomerOrderById(Long id) {
        customerOrderRepository.deleteById(id);
    }

    private CustomerOrderDTO saveAndReturnDTO(CustomerOrder customerOrder) {
        CustomerOrder savedCustomerOrder = customerOrderRepository.save(customerOrder);

        CustomerOrderDTO returnDTO = customerOrderMapper.fromCustomerOrder(savedCustomerOrder);

        return returnDTO;
    }
}
