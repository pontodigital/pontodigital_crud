package com.digitaldot.employer.service.interfaces;

import com.digitaldot.employer.exceptions.ApiException;

import java.util.List;

public interface IValidator {

    boolean hasErros(Object obj);

    List<String> validate(Object obj) throws ApiException;

    List<String> getAllErrors();
}
