package com.digitaldot.employer.service;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.mapper.UserMapper;
import com.digitaldot.employer.model.User;
import com.digitaldot.employer.model.dto.PageUserDto;
import com.digitaldot.employer.model.dto.UserDto;
import com.digitaldot.employer.repository.user.UserRepository;
import com.digitaldot.employer.service.interfaces.IUserService;
import com.digitaldot.employer.utils.HideLinksUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HideLinksUtils hideLinksUtils;

    //todo new listAll filter and pagination ->refactor
    //por default o valor da page vem 0
    //por default o valor do size vem 5
    public PageUserDto listAll(Pageable pageable) throws ApiException, ValidatorErrorException {

//        if (pageable.getPageNumber() == 1)
//        {
//            pageable.withPage(0);
//        }
//        else
//        {
//            pageable.withPage(pageable.getPageNumber() - 1);
//        }

        Page<User> userPage = userRepository.findAll(pageable);
        CollectionModel<UserDto> userDtos = userMapper.toCollectionLinkDto(userMapper.toArrayDto(userPage.getContent()));
        PageUserDto pageUserDto = new PageUserDto();
        pageUserDto.setUsers(userDtos);
        pageUserDto.setItens(userPage.getSize());
        pageUserDto.setTotalItens(userPage.getTotalElements());
        pageUserDto.setTotalPages(userPage.getTotalPages());
        return pageUserDto;

    }

    @Override
    public UserDto findByQuery(String query) throws ApiException, ValidatorErrorException {

        Optional<User> user = userRepository.findById(query);
        if (user.isPresent())
        {
            return userMapper.toLinkDto(userMapper.toDto(user.get()), hideLinksUtils.hideId());
        }
        user = Optional.ofNullable(userRepository.findByEmail(query));
        if (user.isPresent())
        {
            return userMapper.toLinkDto(userMapper.toDto(user.get()), hideLinksUtils.hideId());
        }

        return userMapper.toLinkDto(userMapper.toDto(Optional.ofNullable(userRepository.findByUsername(query))
                .orElseThrow(() -> new ApiException("No registered user", HttpStatus.NO_CONTENT.value()))), hideLinksUtils.hideId());
    }

    @Override
    public UserDto createUser(UserDto userDto) throws ApiException, ValidatorErrorException {

        User userExists = userRepository.findByEmail(userDto.getEmail());
        if (nonNull(userExists)) {
            throw new ApiException("email already exists", HttpStatus.BAD_REQUEST.value());
        }
        userExists = userRepository.findByUsername(userDto.getUsername());
        if (nonNull(userExists)) {
            throw new ApiException("username already exists", HttpStatus.BAD_REQUEST.value());
        }

        User userDomain = userRepository.save(userMapper.toDomain(userDto));

        return userMapper.toLinkDto(userMapper.toDto(userDomain));
    }

    public UserDto updateUser(String id, UserDto userUpdateDto) throws ApiException, ValidatorErrorException {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ApiException("user not found", HttpStatus.NOT_FOUND.value()));

        Optional<User> userExists = Optional.ofNullable(userRepository.findByUsername(userUpdateDto.getUsername()));
        if (userExists.isPresent())
        {
            if (!userExists.get().getUsername().equals(userUpdateDto.getUsername()))
            {
                throw new ApiException("user already exists with this username", HttpStatus.BAD_REQUEST.value());
            }
        }
        userExists = Optional.ofNullable(userRepository.findByEmail(userUpdateDto.getEmail()));
        if (userExists.isPresent())
        {
            if (!userExists.get().getEmail().equals(userUpdateDto.getEmail()))
            {
                throw new ApiException("user already exists with this email", HttpStatus.BAD_REQUEST.value());
            }
        }

        user.setUsername(userUpdateDto.getUsername());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        user.setAcceptedTerms(userUpdateDto.isAcceptedTerms());
        user.setActive(userUpdateDto.isActive());

        return userMapper.toLinkDto(userMapper.toDto(user), hideLinksUtils.hideEdit());
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
