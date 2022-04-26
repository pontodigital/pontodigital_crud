package com.digitaldot.employer.mapper;

import com.digitaldot.employer.controller.v1.UserController;
import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.model.User;
import com.digitaldot.employer.model.dto.UserDto;
import com.digitaldot.employer.utils.HideLinksUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HideLinksUtils hideLinksUtils;

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

    public UserDto toLinkDto(UserDto userDto) throws ApiException, ValidatorErrorException {
        return toLinkDto(userDto, hideLinksUtils);
    }

    public UserDto toLinkDto(UserDto userDto, HideLinksUtils hideLinksUtils) throws ApiException, ValidatorErrorException {

        if (!hideLinksUtils.isId())
        {
            userDto.add(linkTo(methodOn(UserController.class).findByQuery(userDto.getId())).withSelfRel());
        }
        if (!hideLinksUtils.isEdit())
        {
            userDto.add(linkTo(methodOn(UserController.class).update(userDto.getId(), userDto)).withRel(IanaLinkRelations.EDIT));
        }
        if (!hideLinksUtils.isDelete())
        {
            userDto.add(linkTo(methodOn(UserController.class).delete(userDto.getId())).withRel("delete"));
        }
        if (!hideLinksUtils.isCollection())
        {
            userDto.add(linkTo(methodOn(UserController.class).listAll(Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION));
        }

        return userDto;
    }

    public CollectionModel<UserDto> toCollectionLinkDto(List<UserDto> userDtoList)
            throws ApiException, ValidatorErrorException {

        for (UserDto userDto : userDtoList) {
            toLinkDto(userDto, hideLinksUtils.hideCollection());
        }

        return CollectionModel.of(userDtoList);
    }

}
