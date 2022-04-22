package com.digitaldot.employer.service.interfaces;


import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.exceptions.ApiException;

import java.util.List;

public interface IEmployerService {

    List<EmployerDto> listAll();

    Employer findByName (String name);

    EmployerDto createJoinUser(EmployerDto employerDto) throws ApiException;

    Employer update (Employer employer);

    void delete(String name);

}
