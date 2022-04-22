package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.dto.UserDto;

public interface IUserService {

    UserDto createUser(UserDto user) throws ApiException;

    void deleteUser(String id);
}
