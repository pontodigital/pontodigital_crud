package com.digitaldot.user.service.interfaces;

import com.digitaldot.user.model.dto.PageUserDto;
import com.digitaldot.user.model.dto.UserDto;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    PageUserDto listAll(Pageable pageable) throws ApiException, ValidatorErrorException;

    UserDto findByQuery(String id) throws ApiException, ValidatorErrorException;

    UserDto createUser(UserDto user) throws ApiException, ValidatorErrorException;

    UserDto updateUser(Long id, UserDto userUpdateDto) throws ApiException, ValidatorErrorException;

    void deleteUser(Long id);
}
