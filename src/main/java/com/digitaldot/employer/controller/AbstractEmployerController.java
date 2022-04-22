package com.digitaldot.employer.controller;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.service.interfaces.IEmployerService;
import com.digitaldot.employer.service.interfaces.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractEmployerController {

    @Autowired
    private IEmployerService employerService;

    @Autowired
    private IValidator validator;

    @GetMapping("/-")
    public ResponseEntity<List<EmployerDto>> listAll(){
        return ResponseEntity.ok(employerService.listAll());
    }

    @GetMapping("/-/{id}")
    public ResponseEntity<EmployerDto> findById(@PathVariable String id) throws ApiException {
        return ResponseEntity.ok(employerService.findById(id)) ;
    }

    @PostMapping("/-")
    public ResponseEntity<EmployerDto> createJoinUser(@RequestBody EmployerDto employer)
            throws ApiException, ValidatorErrorException {

        if (validator.hasErros(employer)) {
            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(employerService.createJoinUser(employer));
    }

//    @PutMapping(path = "/{name}")
//    public ResponseEntity<Response<Employer>> update(@PathVariable String name, @Valid @RequestBody Employer employer, BindingResult result){
//        if (result.hasErrors()){
//            List<String> erros = new ArrayList<>();
//            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(new Response<>(erros));
//        }
//
//        employer.setFirstName(name);
//        return ResponseEntity.ok( new Response<>(employerService.update(employer)));
//    }

    @DeleteMapping(value = "/-/{id}")
    public ResponseEntity<Integer> deleteEmployerJoinUser(@PathVariable String id) throws ApiException {
        employerService.deleteEmployerJoinUser(id);
        return ResponseEntity.ok().build();
    }
}
