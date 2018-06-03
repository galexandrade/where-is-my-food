package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.CustomerDTO;
import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.domain.Customer;
import com.whereismyfood.restapi.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}
