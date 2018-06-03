package com.whereismyfood.restapi.api.v1.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Data
public class ProductDTO {
    private String title;
    private String description;
    private String imageUrl;
    private Float price;
}
