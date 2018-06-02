package com.whereismyfood.restapi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Data
public class OrderItem extends BaseEntity {
    @ManyToOne
    CustomerOrder order;

    @ManyToOne
    Product product;
    Integer amount;
}
