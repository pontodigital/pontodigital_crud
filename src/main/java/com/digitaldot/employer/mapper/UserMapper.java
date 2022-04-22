package com.digitaldot.employer.mapper;

import com.digitaldot.employer.model.User;
import com.digitaldot.employer.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

//    public UserDto toUpdateDto(User user) {
//        return mapper.map(user, UserDto.class);
//    }
}
