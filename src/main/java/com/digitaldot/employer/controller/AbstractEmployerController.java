package com.digitaldot.employer.controller;

import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.service.interfaces.IEmployerService;
import com.digitaldot.employer.service.interfaces.IValidator;
import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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

//    @GetMapping(value = "dto")
//    public ResponseEntity<Response<EmployerDto>> findByNameDto(@RequestParam(name = "name") String name){
//        return ResponseEntity.ok(new Response<>(employerService.findByNameDto(name)));
//    }

//    @GetMapping("/")
//    public ResponseEntity<Employer> findByName (@RequestParam(name = "name") String name){
//        return ResponseEntity.ok(employerService.findByName(name));
//    }

    @PostMapping("/-")
    public ResponseEntity<EmployerDto> createJoinUser(@Valid @RequestBody Employer employer, BindingResult result)
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

//    @DeleteMapping(value = "/")
//    public ResponseEntity <Response<Integer>> remover(@RequestParam(name = "name")String name){
//        employerService.delete(name);
//        return ResponseEntity.ok(new Response<>(1));
//    }
}
