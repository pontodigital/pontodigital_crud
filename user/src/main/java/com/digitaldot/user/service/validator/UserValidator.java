package com.digitaldot.user.service.validator;

import com.digitaldot.user.model.dto.UserDto;
import com.digitaldot.validator.AbstractValidator;
import com.digitaldot.validator.ValidatorFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserValidator extends AbstractValidator {

    @Autowired
    private ValidatorFields validatorServices;

    private List<String> errors;

    @Override
    public boolean hasErros(Object obj) {
        return super.hasErros(obj);
    }

    @Override
    public List<String> validate(Object obj) {
        UserDto userDto;
        errors = new ArrayList<>();

        userDto = (UserDto) obj;
        errors.addAll(super.validate(userDto));

        //username
        if (userDto.getUsername().length() < 3 || userDto.getUsername().length() > 10)
        {
            errors.add("username requires minimum 3 letters and maximum 10");
        }
        //email
        if (!validatorServices.isValidEmail(userDto.getEmail()))
        {
            errors.add("email is not valid");
        }
        //password
        if (userDto.getPassword().length() < 4 || userDto.getPassword().length() > 10)
        {
            errors.add("password requires minimum 4 characters and maximum 10");
        }

        setErros(errors);
        return getAllErrors();
    }

    @Override
    public List<String> getAllErrors() {
        return errors;
    }
}
