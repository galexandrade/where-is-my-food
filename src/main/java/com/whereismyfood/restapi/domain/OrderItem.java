package com.whereismyfood.restapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Getter
@Setter
/* To not enter in a cicle reference on Lombok*/
@EqualsAndHashCode(exclude = {"product", "order"})
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
