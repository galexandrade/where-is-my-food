package com.whereismyfood.restapi.repositories;

import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
