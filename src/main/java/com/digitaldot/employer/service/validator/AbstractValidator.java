package com.digitaldot.employer.service.validator;

import com.digitaldot.employer.service.interfaces.IValidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

public abstract class AbstractValidator implements IValidator {

    private List<String> erros;

    @Override
    public boolean hasErros(Object obj) {

        if (!validate(obj).isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public List<String> validate(Object obj) {

        final Field[] fields = obj.getClass().getDeclaredFields();

        erros = new ArrayList<>();

        for (Field field : fields) {

            field.setAccessible(true);

            try
            {
                if (isNull(field))
                {
                    erros.add(field.getName().concat(" is required"));
                }
                else if (field.get(obj) instanceof String)
                {
                    if (isEmpty(field.get(obj)))
                    {
                        erros.add(field.getName().concat(" is blank"));
                    }
                }
            }
            catch(IllegalAccessException e)
            {
                erros.add("an unexpected error occurred".concat(e.getMessage()));
            }
        }

        return erros;
    }

    @Override
    public List<String> getAllErrors() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
