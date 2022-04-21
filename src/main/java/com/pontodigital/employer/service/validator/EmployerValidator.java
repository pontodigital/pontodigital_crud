package com.pontodigital.employer.service.validator;

import com.pontodigital.employer.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployerValidator extends AbstractValidator{

    @Autowired
    private ValidatorFields validatorServices;

    @Override
    public List<String> validate(Object obj) {

        List<String> errors = super.validate(obj);

        if (!(obj instanceof Employer)) {
            return errors;
        }

        Employer employer = (Employer) obj;
        //firstName
        if (employer.getFirstName().length() < 3 || employer.getFirstName().length() > 10)
        {
            errors.add("firstName requires minimum 3 letters and maximum 10");
        }
        if (!employer.getFirstName().matches("^[a-zA-Z]*$"))
        {
            errors.add("only letters is permission in firstName");
        }
        //lastName
        if (employer.getLastName().length() < 3 || employer.getLastName().length() > 25)
        {
            errors.add("lastName requires minimum 3 letters and maximum 25");
        }
        if (!employer.getLastName().matches("^[a-zA-Z\\s]*$"))
        {
            errors.add("only letters is permission in lastName");
        }
        //document
        if (employer.getDocument().length() != 11)
        {
            errors.add("document is need 11 number");
        }
        if (!employer.getDocument().matches("\\d+"))
        {
            errors.add("only number is permission in document");
        }
        String[] cpf = employer.getDocument().split("(?<=\\G.{1})"); //split string 1 in 1
        if (!validatorServices.isValidCpf(Arrays.stream(cpf)
                .mapToInt(Integer::parseInt)
                .toArray()))
        {
            errors.add("document is not valid");
        }
        //phone
        if (employer.getPhone().length() != 11)
        {
            errors.add("phone number is need 11 number");
        }
        if (!employer.getPhone().matches("\\d+"))
        {
            errors.add("only number is permission in phone number");
        }
        if (!validatorServices.isValidNumberPhone(employer.getPhone()))
        {
            errors.add("phone number is not valid");
        }

        setErros(errors);
        return getAllErrors();
    }
}
