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
public class UserRepositoryTest {
    public static final String USER_LOGIN = "g.alex.andrade@gmail.com";
    public static final String USER_PASSWORD = "MySecretKey";
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveTest(){
        long beforeCount = userRepository.count();

        User user = UserRepositoryTest.createObject();

        userRepository.save(user);

        Assert.assertEquals(beforeCount, userRepository.count() - 1);
    }

    @Test
    public void updateTest(){
        User user = UserRepositoryTest.createObject();

        userRepository.save(user);

        user.setPassword(USER_PASSWORD + "NEW");

        User storedUser = userRepository.save(user);

        Assert.assertEquals("Must have the password updated", storedUser.getPassword(), USER_PASSWORD + "NEW");
    }

    @Test
    public void deleteTest(){
        long beforeCount = userRepository.count();

        User user = UserRepositoryTest.createObject();

        userRepository.save(user);

        userRepository.delete(user);

        Assert.assertEquals(beforeCount, userRepository.count());
    }

    public static User createObject(){
        User data = new User();
        data.setLogin(USER_LOGIN);
        data.setPassword(USER_PASSWORD);
        data.setRole(Role.CUSTOMER);

        return data;
    }

}