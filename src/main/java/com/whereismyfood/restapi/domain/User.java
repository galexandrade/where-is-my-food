package com.whereismyfood.restapi.domain;

import com.whereismyfood.restapi.enums.Role;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Data
public class User extends BaseEntity {
    String login;
    String password;
    Role role;
}
