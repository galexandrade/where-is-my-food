package com.whereismyfood.restapi.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Data
public class Customer extends BaseEntity {
    String firstName;
    String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    User user;

    @OneToMany(mappedBy = "customer")
    List<CustomerOrder> orders = new ArrayList<>();
}
