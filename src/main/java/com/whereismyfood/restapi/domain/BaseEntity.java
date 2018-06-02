package com.whereismyfood.restapi.domain;

import javax.persistence.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @Version
    private Long version;

    protected BaseEntity() {
        id = null;
    }

    public Long getId() {
        return id;
    }
}
