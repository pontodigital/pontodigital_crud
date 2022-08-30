package com.digitaldot.employer.facade;

import com.digitaldot.employer.mapper.EmployerMapper;
import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.service.EmployerService;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerFacade {

    @Autowired
    private EmployerService service;
    @Autowired
    private EmployerMapper mapper;

    public Employer getEmployer(String query) throws ValidatorErrorException, ApiException {
        return mapper.toDomain(service.findByQueryEmployer(query));
    }
}
