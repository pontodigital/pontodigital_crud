package com.digitaldot.employer.controller;

import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import com.digitaldot.employer.service.interfaces.IEmployerService;
//import com.digitaldot.employer.service.interfaces.IValidator;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractEmployerController {

    //TODO -> create filter for find employer
    @Autowired
    private IEmployerService employerService;

//    @Autowired
//    private IValidator validator;

    @GetMapping("/find")
    public ResponseEntity<CollectionModel<EmployerDto>> listAllEmployers() throws ApiException, ValidatorErrorException {
        //todo -> paginacao
        return ResponseEntity.ok(employerService.listAllEmployers());
    }

    @GetMapping("/find/query")
    public ResponseEntity<EmployerDto> findByQueryEmployer(@RequestParam(name = "value") String query)
            throws ApiException, ValidatorErrorException {

        return ResponseEntity.ok(employerService.findByQueryEmployer(query)) ;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployerDto> createEmployer(@RequestBody EmployerDto employer)
            throws ApiException, ValidatorErrorException {

//        if (validator.hasErros(employer)) {
//            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(employerService.createEmployer(employer));
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<EmployerUpdateDto> updateEmployer(@PathVariable String id, @RequestBody EmployerUpdateDto employer)
            throws ApiException, ValidatorErrorException {

//        if (validator.hasErros(employer)) {
//            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
//        }

        return ResponseEntity.ok(employerService.updateEmployer(id, employer));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable String id) throws ApiException, ValidatorErrorException {
        employerService.deleteEmployer(id);
        return ResponseEntity.noContent().build();
    }

}
