package com.digitaldot.service;

import com.digitaldot.exceptions.ApiException;
import com.digitaldot.exceptions.ValidatorErrorException;
import com.digitaldot.mapper.UserMapper;
import com.digitaldot.model.User;
import com.digitaldot.model.dto.PageUserDto;
import com.digitaldot.model.dto.UserDto;
import com.digitaldot.repository.UserRepository;
import com.digitaldot.service.interfaces.IUserService;
import com.digitaldot.utils.HideLinksUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HideLinksUtils hideLinksUtils;

    public PageUserDto listAll(Pageable pageable) throws ApiException, ValidatorErrorException {

        pageable.withPage(paginationNumber(pageable));
        PageUserDto pageUserDto = userMapper.toPage(repository.findAll(pageable));

        if (pageUserDto.getTotalItens() == 0)
        {
            throw new ApiException("Users not found", HttpStatus.NO_CONTENT.value());
        }

        return pageUserDto;
    }

    @Override
    public UserDto findByQuery(String query) throws ApiException, ValidatorErrorException {

        Optional<User> user;

        if (query.matches("[0-9]+")) {
            user = repository.findById(Long.valueOf(query));
            if (user.isPresent())
            {
                return userMapper.toLinkDto(userMapper.toDto(user.get()), hideLinksUtils.hideId());
            }
        }

        user = Optional.ofNullable(repository.findByEmail(query));
        if (user.isPresent())
        {
            return userMapper.toLinkDto(userMapper.toDto(user.get()), hideLinksUtils.hideId());
        }

        return userMapper.toLinkDto(userMapper.toDto(Optional.ofNullable(repository.findByUsername(query))
                .orElseThrow(() -> new ApiException("No registered user", HttpStatus.NO_CONTENT.value()))), hideLinksUtils.hideId());
    }

    //TODO: create service for join with employer to user -> remember transctional

    @Override
    public UserDto createUser(UserDto userDto) throws ApiException, ValidatorErrorException {

        //TODO: criar estrutura para receber todos os possiveis erros e devolve-los para o cliente
        User userExists = repository.findByEmail(userDto.getEmail());
        if (nonNull(userExists)) {
            throw new ApiException("email already exists", HttpStatus.BAD_REQUEST.value());
        }
        userExists = repository.findByUsername(userDto.getUsername());
        if (nonNull(userExists)) {
            throw new ApiException("username already exists", HttpStatus.BAD_REQUEST.value());
        }

        User user = userMapper.toDomain(userDto);
        User userDomain = repository.save(user);

        return userMapper.toLinkDto(userMapper.toDto(userDomain));
    }

    //TODO: refactor for criteria and add function generic
    public UserDto updateUser(Long id, UserDto userUpdateDto) throws ApiException, ValidatorErrorException {

        User user = repository.findById(id)
                .orElseThrow( () -> new ApiException("user not found", HttpStatus.NOT_FOUND.value()));

        Optional<User> userExists = Optional.ofNullable(repository.findByUsername(userUpdateDto.getUsername()));
        if (userExists.isPresent())
        {
            if (!userExists.get().getUsername().equals(userUpdateDto.getUsername()))
            {
                throw new ApiException("user already exists with this username", HttpStatus.BAD_REQUEST.value());
            }
        }
        userExists = Optional.ofNullable(repository.findByEmail(userUpdateDto.getEmail()));
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

        //Update and persist in real-time
        user = repository.save(user);

        return userMapper.toLinkDto(userMapper.toDto(user), hideLinksUtils.hideEdit());
    }

    //TODO: Entender se deletar logicamente sera mais viavel
    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private int paginationNumber(Pageable pageable) {

        if (pageable.getPageNumber() == 0 || pageable.getPageNumber() == 1 )
        {
            return 0;
        }
        else
        {
            return pageable.getPageNumber() - 1;
        }
    }
}
