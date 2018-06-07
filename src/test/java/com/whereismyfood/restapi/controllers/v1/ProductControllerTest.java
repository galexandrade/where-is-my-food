package com.whereismyfood.restapi.controllers.v1;

import com.whereismyfood.restapi.api.v1.mapper.ProductMapper;
import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.controllers.RestResponseEntityExceptionHandler;
import com.whereismyfood.restapi.domain.Product;
import com.whereismyfood.restapi.repositories.ProductRepositoryTest;
import com.whereismyfood.restapi.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by Alex P. Andrade on 06/06/2018.
 */
public class ProductControllerTest {
    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    ProductMapper productMapper = ProductMapper.INSTANCE;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllProducts() throws Exception {
        //Given
        ProductDTO product1 = productMapper.fromProduct(ProductRepositoryTest.createObject());
        ProductDTO product2 = productMapper.fromProduct(ProductRepositoryTest.createObject());

        List<ProductDTO> products = Arrays.asList(product1, product2);

        //When
        when(productService.getAllProducts()).thenReturn(products);

        //Then
        mockMvc.perform(get(ProductController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(2)));
    }

}