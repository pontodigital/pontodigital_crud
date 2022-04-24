package com.digitaldot.employer.mapper;

import com.digitaldot.employer.model.User;
import com.digitaldot.employer.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    ModelMapper mapper;

    public UserDto toDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    public User toDomain(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public List<UserDto> toArrayDto(List<User> userList) {
        return userList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<User> toArrayDomain(List<UserDto> userList) {
        return userList
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

}
