package com.whereismyfood.restapi.domain;

import com.whereismyfood.restapi.enums.Role;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Data
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private Role role;
}
