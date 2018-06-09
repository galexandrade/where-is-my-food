package com.whereismyfood.restapi.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {
    private List<CustomerDTO> customers;
}
