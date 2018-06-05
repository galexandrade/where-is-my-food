package com.whereismyfood.restapi.api.v1.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Data
public class CustomerOrderDTO {
    private CustomerDTO customer;
    private List<OrderItemDTO> items;
}
