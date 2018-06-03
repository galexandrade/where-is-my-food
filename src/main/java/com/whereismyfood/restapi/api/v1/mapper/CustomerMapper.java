package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.UserDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
