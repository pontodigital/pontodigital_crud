package com.digitaldot.employer.controller;


import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.model.dto.UserDto;
import com.digitaldot.employer.service.UserService;
import com.digitaldot.employer.service.interfaces.IUserService;
import com.digitaldot.employer.service.interfaces.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IValidator validator;

    @GetMapping("/-")
    public ResponseEntity<CollectionModel<UserDto>> listAll() throws ApiException {
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/-/query")
    public ResponseEntity<UserDto> findByQuery(@RequestParam(name = "value") String query) throws ApiException {
        return ResponseEntity.ok(userService.findByQuery(query));
    }
    @PostMapping("/-")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) throws ApiException, ValidatorErrorException {

        if (validator.hasErros(userDto)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping("/-/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto userDto)
            throws ApiException, ValidatorErrorException {

        if (validator.hasErros(userDto)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    public ResponseEntity<Integer> delete(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
