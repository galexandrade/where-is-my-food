package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.CustomerOrderDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.CustomerOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Mapper(uses = {CustomerMapper.class, OrderItemMapper.class})
public interface CustomerOrderMapper {
    CustomerOrderMapper INSTANCE = Mappers.getMapper(CustomerOrderMapper.class);

    @Mappings({
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "items", target = "items")
    })
    CustomerOrderDTO fromCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder toCustomerOrder(CustomerOrderDTO customerOrderDTO);
}
