package com.digitaldot.employer.controller;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import com.digitaldot.employer.service.interfaces.IEmployerService;
import com.digitaldot.employer.service.interfaces.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractEmployerController {

    //TODO -> create filter for find employer
    @Autowired
    private IEmployerService employerService;

    @Autowired
    private IValidator validator;

    @GetMapping("/-")
    public ResponseEntity<CollectionModel<EmployerDto>> listAll() throws ApiException {
        return ResponseEntity.ok(employerService.listAll());
    }

    @GetMapping("/-/{id}")
    public ResponseEntity<EmployerDto> findById(@PathVariable String id) throws ApiException {
        return ResponseEntity.ok(employerService.findById(id)) ;
    }

    @PostMapping("/-/join")
    public ResponseEntity<EmployerDto> createJoinUser(@RequestBody EmployerDto employer)
            throws ApiException, ValidatorErrorException {

        if (validator.hasErros(employer)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(employerService.createJoinUser(employer));
    }

    @PutMapping(path = "/-/{id}")
    public ResponseEntity<EmployerUpdateDto> update(@PathVariable String id, @RequestBody EmployerUpdateDto employer)
            throws ApiException, ValidatorErrorException {

        if (validator.hasErros(employer)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.ok(employerService.update(id, employer));
    }

    @DeleteMapping(value = "/-/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) throws ApiException {
        employerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/-/join/{id}")
    public ResponseEntity<Integer> deleteEmployerJoinUser(@PathVariable String id) throws ApiException {
        employerService.deleteEmployerJoinUser(id);
        return ResponseEntity.ok().build();
    }
}
