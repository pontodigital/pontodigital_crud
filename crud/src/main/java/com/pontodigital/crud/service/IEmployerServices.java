package com.pontodigital.crud.service;


import com.github.pontodigital.model.Employer;
import com.pontodigital.crud.model.dto.EmployerDto;

import java.util.List;

public interface IEmployerServices {

    EmployerDto findByNameDto(String name);

    List<Employer> listAll();

    Employer findByName (String nome);

    Employer create(Employer Funcionario);

    Employer update (Employer Funcionario);

    void delete(String nome);

}
