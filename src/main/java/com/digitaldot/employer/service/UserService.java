package com.digitaldot.employer.service;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.mapper.UserMapper;
import com.digitaldot.employer.model.User;
import com.digitaldot.employer.model.dto.UserDto;
import com.digitaldot.employer.repository.user.UserRepository;
import com.digitaldot.employer.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<UserDto> listAll() {
        return userMapper.toArrayDto(userRepository.findAll());
    }

    @Override
    public UserDto findByQuery(String query) throws ApiException {

        Optional<User> user = userRepository.findById(query);
        if (user.isPresent())
        {
            return userMapper.toDto(user.get());
        }
        user = Optional.ofNullable(userRepository.findByEmail(query));
        if (user.isPresent())
        {
            return userMapper.toDto(user.get());
        }

        return userMapper.toDto(Optional.ofNullable(userRepository.findByUsername(query))
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND.value())));
    }

    @Override
    public UserDto createUser(UserDto user) throws ApiException {

        User userExists = userRepository.findByEmail(user.getEmail());
        if (nonNull(userExists)) {
            throw new ApiException("email already exists", HttpStatus.BAD_REQUEST.value());
        }
        userExists = userRepository.findByUsername(user.getUsername());
        if (nonNull(userExists)) {
            throw new ApiException("username already exists", HttpStatus.BAD_REQUEST.value());
        }

        User userDomain = userRepository.save(userMapper.toDomain(user));

        return userMapper.toDto(userDomain);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
