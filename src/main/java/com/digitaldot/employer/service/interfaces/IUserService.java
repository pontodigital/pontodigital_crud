package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.dto.UserDto;
import org.springframework.hateoas.CollectionModel;

public interface IUserService {

    CollectionModel<UserDto> listAll() throws ApiException;

    UserDto findByQuery(String id) throws ApiException;

    UserDto createUser(UserDto user) throws ApiException;

    UserDto updateUser(String id, UserDto userUpdateDto) throws ApiException;

    void deleteUser(String id);
}
