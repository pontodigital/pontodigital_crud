package com.digitaldot.employer.mapper;

import com.digitaldot.employer.controller.v1.EmployerController;
import com.digitaldot.employer.controller.v1.UserController;
import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.User;
import com.digitaldot.employer.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    public UserDto toLinkDto(UserDto userDto) throws ApiException {

        userDto.add(linkTo(methodOn(UserController.class).findByQuery(userDto.getId()))
                .withSelfRel());
        userDto.add(linkTo(methodOn(UserController.class).findByQuery(userDto.getUsername()))
                .withSelfRel());
        userDto.add(linkTo(methodOn(UserController.class).findByQuery(userDto.getEmail()))
                .withSelfRel());

        userDto.add(linkTo(methodOn(UserController.class).listAll()).withRel(IanaLinkRelations.COLLECTION));

        return userDto;
    }

    public CollectionModel<UserDto> toCollectionLinkDto(List<UserDto> userDtoList) throws ApiException {

        List<UserDto> userAuxList = new ArrayList<>();

        for (UserDto userDto : userDtoList) {
            userAuxList.add(toLinkDto(userDto));
        }

        return CollectionModel.of(userAuxList).add(linkTo(methodOn(UserController.class).listAll())
                .withRel(IanaLinkRelations.COLLECTION));
    }

}
