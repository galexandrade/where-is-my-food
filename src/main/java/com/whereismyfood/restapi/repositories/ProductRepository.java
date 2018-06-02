package com.whereismyfood.restapi.repositories;

import com.whereismyfood.restapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
