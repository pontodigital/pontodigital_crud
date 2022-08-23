package com.digitaldot.service.interfaces;

import com.digitaldot.exceptions.ApiException;
import com.digitaldot.exceptions.ValidatorErrorException;
import com.digitaldot.model.dto.EmployerDto;
import com.digitaldot.model.dto.EmployerUpdateDto;
import org.springframework.hateoas.CollectionModel;

public interface IEmployerService {

    CollectionModel<EmployerDto> listAll() throws ApiException, ValidatorErrorException;

    EmployerDto findByQuery(String query) throws ApiException, ValidatorErrorException;

    EmployerDto createJoinUser(EmployerDto employerDto) throws ApiException, ValidatorErrorException;

    EmployerUpdateDto update (String id, EmployerUpdateDto employer) throws ApiException, ValidatorErrorException;

    void delete(String id) throws ApiException, ValidatorErrorException;

    void deleteEmployerJoinUser(String id) throws ApiException, ValidatorErrorException;

}
