package com.whereismyfood.restapi.api.v1.mapper;

import com.whereismyfood.restapi.api.v1.model.UserDTO;
import com.whereismyfood.restapi.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Alex P. Andrade on 02/06/2018.
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //@Mapping(source = "login", target = "login")
    //@Mapping(source = "password", target = "password")
    UserDTO userToUserDTO(User user);

    //@Mapping(source = "login", target = "login")
    //@Mapping(source = "password", target = "password")
    User userDTOToUser(UserDTO userDTO);
}
