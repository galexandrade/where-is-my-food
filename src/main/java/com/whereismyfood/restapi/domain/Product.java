package com.whereismyfood.restapi.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Entity
@Data
public class Product extends BaseEntity {
    String title;
    String description;
    String imageUrl;
    Float price;
}
