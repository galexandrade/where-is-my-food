package com.whereismyfood.restapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Data
/* To not enter in a cicle reference on Lombok*/
@EqualsAndHashCode(exclude = {"items", "customer"})
public class CustomerOrder extends BaseEntity {
    @OneToMany(mappedBy = "order")
    List<OrderItem> items = new ArrayList<>();
    @ManyToOne
    Customer customer;
}
