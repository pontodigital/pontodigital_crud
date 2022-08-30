package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.hateoas.CollectionModel;

public interface IEmployerService {

    CollectionModel<EmployerDto> listAllEmployers() throws ApiException, ValidatorErrorException;

    EmployerDto findByQueryEmployer(String query) throws ApiException, ValidatorErrorException;

    EmployerDto createEmployer(EmployerDto employerDto) throws ApiException, ValidatorErrorException;

    EmployerUpdateDto updateEmployer(String id, EmployerUpdateDto employer) throws ApiException, ValidatorErrorException;

    void deleteEmployer(String id) throws ApiException, ValidatorErrorException;

}
