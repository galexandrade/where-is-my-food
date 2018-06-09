package com.whereismyfood.restapi.controllers.v1;

import com.whereismyfood.restapi.api.v1.mapper.ProductMapper;
import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.controllers.RestResponseEntityExceptionHandler;
import com.whereismyfood.restapi.repositories.ProductRepositoryTest;
import com.whereismyfood.restapi.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.whereismyfood.restapi.controllers.v1.AbstractRestControllerTest.asJsonString;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void getProductById() throws Exception {
        //Given
        ProductDTO product = productMapper.fromProduct(ProductRepositoryTest.createObject());

        //When
        when(productService.getProductById(anyLong())).thenReturn(product);

        //Then
        mockMvc.perform(get(ProductController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(product.getTitle())));
    }

    @Test
    public void  createNewProduct() throws Exception{
        //Given
        ProductDTO product = productMapper.fromProduct(ProductRepositoryTest.createObject());

        //When
        when(productService.createNewProduct(product)).thenReturn(product);

        //Then
        mockMvc.perform(post(ProductController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", equalTo(product.getTitle())));
    }

    @Test
    public void  saveProduct() throws Exception{
        //Given
        ProductDTO product = productMapper.fromProduct(ProductRepositoryTest.createObject());

        //When
        when(productService.saveProduct(anyLong(), ArgumentMatchers.any(ProductDTO.class))).thenReturn(product);

        //Then
        mockMvc.perform(put(ProductController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(product.getTitle())))
                .andExpect(jsonPath("$.description", equalTo(product.getDescription())));
    }

    @Test
    public void  patchProduct() throws Exception{
        //Given
        ProductDTO product = productMapper.fromProduct(ProductRepositoryTest.createObject());

        //When
        when(productService.patchProduct(anyLong(), ArgumentMatchers.any(ProductDTO.class))).thenReturn(product);

        //Then
        mockMvc.perform(patch(ProductController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(product.getTitle())))
                .andExpect(jsonPath("$.description", equalTo(product.getDescription())));
    }

    @Test
    public void deleteProduct() throws Exception {

        mockMvc.perform(delete(ProductController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).deleteProductById(anyLong());
    }



}