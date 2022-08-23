package com.digitaldot.service.validator;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidatorFields.class)
@JacksonAnnotation
public @interface ValidField {

    String message() default "Campo invalido";

    TypeValid type() default TypeValid.DEFAULT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    enum TypeValid {
        DEFAULT,
        CPF,
        PHONE,
        EMAIL;
    }
}
