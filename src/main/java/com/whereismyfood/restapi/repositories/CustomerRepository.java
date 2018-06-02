package com.whereismyfood.restapi.repositories;

import com.whereismyfood.restapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
