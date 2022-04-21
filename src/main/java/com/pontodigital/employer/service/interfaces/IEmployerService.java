package com.pontodigital.employer.service.interfaces;


import com.pontodigital.employer.exceptions.ApiException;
import com.pontodigital.employer.model.Employer;
import com.pontodigital.employer.model.dto.EmployerDto;

import java.util.List;

public interface IEmployerService {

    EmployerDto findByNameDto(String name);

    List<EmployerDto> listAll();

    Employer findByName (String name);

    EmployerDto createJoinUser(Employer employer) throws ApiException;

    Employer update (Employer employer);

    void delete(String name);

}
