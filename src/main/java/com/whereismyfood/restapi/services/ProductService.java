package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.model.ProductDTO;

import java.util.List;

/**
 * Created by Alex P. Andrade on 03/06/2018.
 */
public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO createNewProduct(ProductDTO productDTO);

    ProductDTO saveProduct(Long id, ProductDTO productDTO);

    ProductDTO patchProduct(Long id, ProductDTO productDTO);

    void deleteProductById(Long id);
}
