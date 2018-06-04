package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.ProductMapper;
import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.domain.Product;
import com.whereismyfood.restapi.repositories.ProductRepository;
import com.whereismyfood.restapi.repositories.ProductRepositoryTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex P. Andrade on 03/06/2018.
 */
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    private ProductMapper productMapper = ProductMapper.INSTANCE;

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(productMapper, productRepository);
    }

    @Test
    public void getAllProducts() throws Exception {
        //Given
        Product product1 = ProductRepositoryTest.createObject();
        Product product2 = ProductRepositoryTest.createObject();

        //When
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<ProductDTO> products = productService.getAllProducts();

        //Then
        Assert.assertEquals(2, products.size());
    }

    @Test
    public void getProductById() throws Exception {
        //Given
        Product product = ProductRepositoryTest.createObject();

        //When
        when(productRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(product));
        ProductDTO productDTO = productService.getProductById(1L);

        //Then
        assertEquals(product.getTitle(), productDTO.getTitle());
    }

    @Test
    public void createNewProduct() throws Exception {
        //Given
        Product savedProduct = ProductRepositoryTest.createObject();
        ProductDTO productDTO = productMapper.productToProductDTO(savedProduct);

        //When
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        ProductDTO savedProductDTO = productService.createNewProduct(productDTO);

        //Then
        Assert.assertEquals(productDTO.getTitle(), savedProductDTO.getTitle());
    }

    @Test
    public void saveProduct() throws Exception {
        //Given
        Product savedProduct = ProductRepositoryTest.createObject();
        savedProduct.setId(1L);

        ProductDTO productDTO = productMapper.productToProductDTO(savedProduct);

        //When
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        ProductDTO savedProductDTO = productService.saveProduct(1L, productDTO);

        //Then
        Assert.assertEquals(productDTO.getTitle(), savedProductDTO.getTitle());
    }

    @Test
    public void patchProduct() throws Exception {
        //Given
        Product savedProduct = new Product();
        savedProduct.setId(1L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("BBQ Beef Brisket Sandwiches");

        //When
        when(productRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(savedProduct));
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);

        ProductDTO savedProductDTO = productService.patchProduct(1L, productDTO);

        //Then
        verify(productRepository, times(1)).save(argumentCaptor.capture());
        Product captureProduct = argumentCaptor.getValue();

        Assert.assertNull(captureProduct.getImageUrl());
    }

    @Test
    public void deleteProductById() throws Exception {
        //Given
        Long idToDelete = 1L;

        //When
        productService.deleteProductById(idToDelete);

        //Then
        verify(productRepository, times(1)).deleteById(anyLong());

    }

}