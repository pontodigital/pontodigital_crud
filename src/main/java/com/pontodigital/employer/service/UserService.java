package com.pontodigital.employer.service;

import com.pontodigital.employer.exceptions.ApiException;
import com.pontodigital.employer.mapper.UserMapper;
import com.pontodigital.employer.model.Employer;
import com.pontodigital.employer.model.User;
import com.pontodigital.employer.model.dto.UserDto;
import com.pontodigital.employer.repository.user.UserRepository;
import com.pontodigital.employer.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto create(User user) throws ApiException {

        User userExists = userRepository.findByEmail(user.getEmail());
        if (nonNull(userExists)) {
            throw new ApiException("email already exists", HttpStatus.BAD_REQUEST.value());
        }
        userExists = userRepository.findByUsername(user.getUsername());
        if (nonNull(userExists)) {
            throw new ApiException("username already exists", HttpStatus.BAD_REQUEST.value());
        }

        return userMapper.toDto(userRepository.save(user));
    }
}
