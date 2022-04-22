package com.digitaldot.employer.service.validator;

import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class UserValidator extends EmployerValidator {

    @Autowired
    private ValidatorFields validatorServices;

    @Override
    public List<String> validate(Object obj) {
        UserDto userDto;
        List<String> errors = super.validate(obj);

        if (obj instanceof EmployerDto)
        {
            EmployerDto employerDto = (EmployerDto) obj;
            userDto = employerDto.getUser();
        }
        else if (obj instanceof UserDto)
        {
            userDto = (UserDto) obj;
        }
        else
        {
            return getAllErrors();
        }

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
        if (userDto.getPassword().length() < 4 || userDto.getPassword().length() > 8)
        {
            errors.add("password requires minimum 4 characters and maximum 10");
        }

        setErros(errors);
        return getAllErrors();
    }
}
