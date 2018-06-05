package com.whereismyfood.restapi.api.v1.model;

import com.whereismyfood.restapi.domain.OrderItem;
import lombok.Data;

import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Data
public class OrderItemDTO {
    private ProductDTO product;
    private Integer amount;
}
