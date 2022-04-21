//package com.pontodigital.employer.service;
//
//import com.pontodigital.employer.exceptions.ValidatorErrorException;
//import com.pontodigital.employer.repository.employer.EmployerRepository;
//import com.pontodigital.employer.service.interfaces.IValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.BindingResult;
//
//import java.util.stream.Collectors;
//
//@Service
//public class ValidationService implements IValidator {
//
//    @Autowired
//    private EmployerRepository employerRepository;
//
//    public void bindingResult(BindingResult result) throws ValidatorErrorException {
//
//        throw new ValidatorErrorException(result.getAllErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList()), HttpStatus.BAD_REQUEST.value());
//    }
//}
