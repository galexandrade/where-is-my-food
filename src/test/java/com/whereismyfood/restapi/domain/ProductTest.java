package com.whereismyfood.restapi.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
public class ProductTest {
    Product product;

    public static final Long PRODUCT_ID = 1L;
    public static final String PRODUCT_TITLE = "BBQ Beef Brisket Sandwiches";
    public static final String PRODUCT_DESCRIPTION = "Slow-cooked seasoned brisket shredded and prepared for tantalizing barbeque sandwiches. Enjoy on your favorite bread.";
    public static final String PRODUCT_IMAGE_URL = "https://images.media-allrecipes.com/userphotos/560x315/4415106.jpg";
    public static final Float PRODUCT_PRICE = 10.5F;

    @Before
    public void setUp() throws Exception {
        product = new Product();
    }

    @Test
    public void getId() throws Exception {
        product.setId(PRODUCT_ID);
        assertEquals(PRODUCT_ID, product.getId());
    }

    @Test
    public void getTitle() throws Exception {
        product.setTitle(PRODUCT_TITLE);
        assertEquals(PRODUCT_TITLE, product.getTitle());
    }

    @Test
    public void getDescription() throws Exception {
        product.setDescription(PRODUCT_DESCRIPTION);
        assertEquals(PRODUCT_DESCRIPTION, product.getDescription());
    }

    @Test
    public void getImageUrl() throws Exception {
        product.setImageUrl(PRODUCT_IMAGE_URL);
        assertEquals(PRODUCT_IMAGE_URL, product.getImageUrl());
    }

    @Test
    public void getPrice() throws Exception {
        product.setPrice(PRODUCT_PRICE);
        assertEquals(PRODUCT_PRICE, product.getPrice());
    }

}