package com.whereismyfood.restapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Data
public class OrderItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CustomerOrder order;

    @ManyToOne
    private Product product;
    private Integer amount;
}
