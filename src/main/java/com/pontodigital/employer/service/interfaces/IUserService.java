package com.pontodigital.employer.service.interfaces;

import com.pontodigital.employer.exceptions.ApiException;
import com.pontodigital.employer.model.User;
import com.pontodigital.employer.model.dto.UserDto;

public interface IUserService {

    UserDto create(User user) throws ApiException;
}
