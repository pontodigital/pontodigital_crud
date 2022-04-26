package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.model.dto.UserDto;
import org.springframework.hateoas.CollectionModel;

public interface IUserService {

    CollectionModel<UserDto> listAll() throws ApiException, ValidatorErrorException;

    UserDto findByQuery(String id) throws ApiException, ValidatorErrorException;

    UserDto createUser(UserDto user) throws ApiException, ValidatorErrorException;

    UserDto updateUser(String id, UserDto userUpdateDto) throws ApiException, ValidatorErrorException;

    void deleteUser(String id);
}
