package com.digitaldot.employer.service.interfaces;


import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;

import java.util.List;

public interface IEmployerService {

    List<EmployerDto> listAll();

    EmployerDto findById(String id) throws ApiException;

    EmployerDto createJoinUser(EmployerDto employerDto) throws ApiException;

    EmployerUpdateDto update (String id, EmployerUpdateDto employer) throws ApiException;

    void deleteEmployerJoinUser(String id) throws ApiException;

}
