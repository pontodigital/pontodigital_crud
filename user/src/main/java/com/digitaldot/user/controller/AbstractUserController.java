package com.digitaldot.user.controller;

import com.digitaldot.user.model.dto.PageUserDto;
import com.digitaldot.user.model.dto.UserDto;
import com.digitaldot.user.service.interfaces.IUserService;
import com.digitaldot.utils.hateos.HeadersUtil;
import com.digitaldot.validator.IValidator;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractUserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private HeadersUtil headersUtil;
    @Autowired
    private IValidator validator;

    @GetMapping("/findAll")
    public ResponseEntity<CollectionModel<UserDto>> listAll(@PageableDefault(size = 5) Pageable page)
            throws ApiException, ValidatorErrorException {

            PageUserDto pageUserDto = userService.listAll(page);
            HttpHeaders headers = headersUtil.getHeadersPage(String.valueOf(pageUserDto.getItens()),
                    String.valueOf(pageUserDto.getTotalItens()),
                    String.valueOf(pageUserDto.getTotalPages()));

        return ResponseEntity.ok().headers(headers).body(pageUserDto.getUsers());
    }

    @GetMapping("/find/query")
    public ResponseEntity<UserDto> findByQuery(@RequestParam(name = "value") String query) throws ApiException,
            ValidatorErrorException {

        return ResponseEntity.ok(userService.findByQuery(query));
    }
    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) throws ApiException, ValidatorErrorException {

        if (validator.hasErros(userDto)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) throws ApiException,
            ValidatorErrorException {

        if (validator.hasErros(userDto)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
