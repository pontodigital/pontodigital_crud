package com.digitaldot.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            return isValidEmail(s);
        }

        return false;
    }

    public boolean isValidEmail(String email) {

        if (email.length() > 0)
        {
            String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);

            if (matcher.matches())
            {
                return true;
            }
        }

        return false;
    }

    public boolean isValidNumberPhone(String number) {
        number = checkHasDDD(number);
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;

        try
        {
            phoneNumber = phoneUtil.parse(number, "BR");
        }
        catch (NumberParseException e) {
            e.printStackTrace();
            return false;
        }

        return phoneUtil.isValidNumber(phoneNumber);
    }

    //It removed the ddd and not validate
    private String checkHasDDD(String number) {
        if (number.length() == 12) {
            return number.substring(2, 12);
        }
        return number;
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
