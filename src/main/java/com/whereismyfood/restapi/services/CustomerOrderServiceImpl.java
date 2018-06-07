package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.CustomerOrderMapper;
import com.whereismyfood.restapi.api.v1.model.CustomerOrderDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.exceptions.ResourceNotFoundException;
import com.whereismyfood.restapi.repositories.CustomerOrderRepository;
import com.whereismyfood.restapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Alex P. Andrade on 04/06/2018.
 */
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderMapper customerOrderMapper = CustomerOrderMapper.INSTANCE;
    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerRepository customerRepository;

    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository, CustomerRepository customerRepository) {
        this.customerOrderRepository = customerOrderRepository;
        this.customerRepository = customerRepository;
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
    public CustomerOrderDTO createNewCustomerOrder(Long idCustomer, CustomerOrderDTO customerOrderDTO) {
        Optional<Customer> customer = customerRepository.findById(idCustomer);

        if(!customer.isPresent())
            new ResourceNotFoundException();

        CustomerOrder customerOrder = customerOrderMapper.toCustomerOrder(customerOrderDTO);
        customerOrder.getCustomer().setId(customer.get().getId());

        return this.saveAndReturnDTO(customerOrder);
    }

    @Override
    public CustomerOrderDTO saveCustomerOrder(Long id, CustomerOrderDTO customerOrderDTO) {
        CustomerOrder customerOrder = customerOrderMapper.toCustomerOrder(customerOrderDTO);
        customerOrder.setId(id);

        return this.saveAndReturnDTO(customerOrder);
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
