package com.digitaldot.employer.service.validator;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public final class ValidatorFields implements ConstraintValidator<ValidField, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        Map<String, String> constraintsProvides = new HashMap<>();
        constraintsProvides.put("type", ((ConstraintValidatorContextImpl)constraintValidatorContext)
                .getConstraintDescriptor()
                .getAttributes()
                .get("type").toString());

        if (constraintsProvides.containsValue(ValidField.TypeValid.CPF.name())) {
            String[] cpf = s.split("(?<=\\G.{1})");
            return isValidCpf(Arrays.stream(cpf)
                    .mapToInt(Integer::parseInt)
                    .toArray());

        }
        if (constraintsProvides.containsValue(ValidField.TypeValid.PHONE.name())) {
            return isValidNumberPhone(s);
        }
        if (constraintsProvides.containsValue(ValidField.TypeValid.EMAIL.name())) {
            //TODO -> necessary implementation
            return true;
        }

        return false;
    }

    public boolean isValidNumberPhone(String number) {
        //TODO -> necessary implementation

        return true;
    }

    public boolean isValidCpf(int[] number) {

        if (!isEmpty(number)) {

            if (checkDigitsCpf(10, number) != number[9]) //check first digit
            {
                return false;
            }
            else return checkDigitsCpf(11, number) == number[10]; //check final digit

        }

        return false;
    }

    public int checkDigitsCpf(int multiplication, int[] number) {

        int calc = 0;
        int count = multiplication-1;

        for (int i = 0; count > i; i++) {
            calc += number[i] * multiplication;
            multiplication = multiplication - 1;
        }

        int resto = calc % 11;
        return resto <= 0 ? resto : (11 - resto);
    }

}
