package com.whereismyfood.restapi.repositories;

import com.whereismyfood.restapi.domain.Product;
import com.whereismyfood.restapi.domain.User;
import com.whereismyfood.restapi.enums.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    public static final String PRODUCT_TITLE = "BBQ Beef Brisket Sandwiches";
    public static final String PRODUCT_DESCRIPTION = "Slow-cooked seasoned brisket shredded and prepared for tantalizing barbeque sandwiches. Enjoy on your favorite bread.";
    public static final String PRODUCT_IMAGE_URL = "https://images.media-allrecipes.com/userphotos/560x315/4415106.jpg";
    public static final Float PRODUCT_PRICE = 10.5F;
    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveTest(){
        long beforeCount = productRepository.count();

        Product product = ProductRepositoryTest.createObject();

        productRepository.save(product);

        Assert.assertEquals(beforeCount, productRepository.count() - 1);
    }

    @Test
    public void updateTest(){
        Product product = ProductRepositoryTest.createObject();

        productRepository.save(product);

        product.setTitle(PRODUCT_TITLE + "NEW");

        Product storedProduct = productRepository.save(product);

        Assert.assertEquals("Must have the password updated", storedProduct.getTitle(), PRODUCT_TITLE + "NEW");
    }

    @Test
    public void deleteTest(){
        long beforeCount = productRepository.count();

        Product product = ProductRepositoryTest.createObject();

        productRepository.save(product);

        productRepository.delete(product);

        Assert.assertEquals(beforeCount, productRepository.count());
    }

    public static Product createObject(){
        Product data = new Product();
        data.setTitle(PRODUCT_TITLE);
        data.setDescription(PRODUCT_DESCRIPTION);
        data.setImageUrl(PRODUCT_IMAGE_URL);
        data.setPrice(PRODUCT_PRICE);

        return data;
    }

}