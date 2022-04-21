package com.pontodigital.employer.service.interfaces;

import com.pontodigital.employer.exceptions.ApiException;

import java.util.List;

public interface IValidator {

    boolean hasErros(Object obj);

    List<String> validate(Object obj) throws ApiException;

    List<String> getAllErrors();
}
