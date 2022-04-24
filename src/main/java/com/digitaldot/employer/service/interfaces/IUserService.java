package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> listAll();

    UserDto createUser(UserDto user) throws ApiException;

    void deleteUser(String id);

    UserDto findByQuery(String id) throws ApiException;
}
