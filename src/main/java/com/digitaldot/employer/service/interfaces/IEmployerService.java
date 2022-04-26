package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import org.springframework.hateoas.CollectionModel;

public interface IEmployerService {

    CollectionModel<EmployerDto> listAll() throws ApiException, ValidatorErrorException;

    EmployerDto findByQuery(String query) throws ApiException, ValidatorErrorException;

    EmployerDto createJoinUser(EmployerDto employerDto) throws ApiException, ValidatorErrorException;

    EmployerUpdateDto update (String id, EmployerUpdateDto employer) throws ApiException, ValidatorErrorException;

    void delete(String id) throws ApiException, ValidatorErrorException;

    void deleteEmployerJoinUser(String id) throws ApiException, ValidatorErrorException;

}
