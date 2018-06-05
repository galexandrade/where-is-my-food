package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.CustomerOrderDTO;
import com.whereismyfood.restapi.api.v1.model.OrderItemDTO;
import com.whereismyfood.restapi.domain.CustomerOrder;
import com.whereismyfood.restapi.domain.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Mapper(uses = {ProductMapper.class})
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO fromOrderItem(OrderItem customerOrder);

    OrderItem toOrderItem(OrderItemDTO orderItemDTO);
}
