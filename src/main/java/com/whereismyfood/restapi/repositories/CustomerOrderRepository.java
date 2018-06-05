package com.whereismyfood.restapi.repositories;

import com.whereismyfood.restapi.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByCustomerId(Long customerId);
}
