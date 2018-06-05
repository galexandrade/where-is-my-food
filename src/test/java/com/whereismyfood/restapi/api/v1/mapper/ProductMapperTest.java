package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.domain.Product;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alex P. Andrade on 03/06/2018.
 */
public class ProductMapperTest {
    public static final String PRODUCT_TITLE = "BBQ Beef Brisket Sandwiches";
    public static final String PRODUCT_DESCRIPTION = "Slow-cooked seasoned brisket shredded and prepared for tantalizing barbeque sandwiches. Enjoy on your favorite bread.";
    public static final String PRODUCT_IMAGE_URL = "https://images.media-allrecipes.com/userphotos/560x315/4415106.jpg";
    public static final Float PRODUCT_PRICE = 10.5F;

    ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    public void fromProduct() throws Exception {
        //Given
        Product product = new Product();
        product.setTitle(PRODUCT_TITLE);
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setImageUrl(PRODUCT_IMAGE_URL);
        product.setPrice(PRODUCT_PRICE);

        //When
        ProductDTO productDTO = productMapper.fromProduct(product);

        //Then
        Assert.assertEquals(product.getTitle(), productDTO.getTitle());
        Assert.assertEquals(product.getDescription(), productDTO.getDescription());
        Assert.assertEquals(product.getPrice(), productDTO.getPrice());
        Assert.assertEquals(product.getImageUrl(), productDTO.getImageUrl());
    }

    @Test
    public void toProduct() throws Exception {
        //Given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle(PRODUCT_TITLE);
        productDTO.setDescription(PRODUCT_DESCRIPTION);
        productDTO.setImageUrl(PRODUCT_IMAGE_URL);
        productDTO.setPrice(PRODUCT_PRICE);

        //When
        Product product = productMapper.toProduct(productDTO);

        //Then
        Assert.assertEquals(productDTO.getTitle(), product.getTitle());
        Assert.assertEquals(productDTO.getDescription(), product.getDescription());
        Assert.assertEquals(productDTO.getPrice(), product.getPrice());
        Assert.assertEquals(productDTO.getImageUrl(), product.getImageUrl());
    }

}