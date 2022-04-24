package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import org.springframework.hateoas.CollectionModel;

public interface IEmployerService {

    CollectionModel<EmployerDto> listAll() throws ApiException;

    EmployerDto findByQuery(String query) throws ApiException;

    EmployerDto createJoinUser(EmployerDto employerDto) throws ApiException;

    EmployerUpdateDto update (String id, EmployerUpdateDto employer) throws ApiException;

    void delete(String id) throws ApiException;

    void deleteEmployerJoinUser(String id) throws ApiException;

}
