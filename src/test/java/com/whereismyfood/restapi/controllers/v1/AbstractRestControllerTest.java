package com.whereismyfood.restapi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Alex P. Andrade on 08/06/2018.
 */
public abstract class AbstractRestControllerTest {
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
