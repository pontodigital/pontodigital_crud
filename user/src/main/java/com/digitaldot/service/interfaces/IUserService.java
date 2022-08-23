package com.digitaldot.service.interfaces;

import com.digitaldot.exceptions.ApiException;
import com.digitaldot.exceptions.ValidatorErrorException;
import com.digitaldot.model.dto.PageUserDto;
import com.digitaldot.model.dto.UserDto;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    PageUserDto listAll(Pageable pageable) throws ApiException, ValidatorErrorException;

    UserDto findByQuery(String id) throws ApiException, ValidatorErrorException;

    UserDto createUser(UserDto user) throws ApiException, ValidatorErrorException;

    UserDto updateUser(String id, UserDto userUpdateDto) throws ApiException, ValidatorErrorException;

    void deleteUser(String id);
}
