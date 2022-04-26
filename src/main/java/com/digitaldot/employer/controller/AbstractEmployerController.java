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

    @GetMapping("/find")
    public ResponseEntity<CollectionModel<EmployerDto>> listAll() throws ApiException, ValidatorErrorException {
        //todo -> paginacao
        return ResponseEntity.ok(employerService.listAll());
    }

    @GetMapping("/find/query")
    public ResponseEntity<EmployerDto> findByQuery(@RequestParam(name = "value") String query)
            throws ApiException, ValidatorErrorException {

        return ResponseEntity.ok(employerService.findByQuery(query)) ;
    }

    @PostMapping("/create/join-user")
    public ResponseEntity<EmployerDto> createJoinUser(@RequestBody EmployerDto employer)
            throws ApiException, ValidatorErrorException {

        if (validator.hasErros(employer)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(employerService.createJoinUser(employer));
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<EmployerUpdateDto> update(@PathVariable String id, @RequestBody EmployerUpdateDto employer)
            throws ApiException, ValidatorErrorException {

        if (validator.hasErros(employer)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.ok(employerService.update(id, employer));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws ApiException, ValidatorErrorException {
        employerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/join-user/{id}")
    public ResponseEntity<Void> deleteEmployerJoinUser(@PathVariable String id) throws ApiException, ValidatorErrorException {
        employerService.deleteEmployerJoinUser(id);
        return ResponseEntity.noContent().build();
    }

}
