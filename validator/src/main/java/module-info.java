module validator {

    exports com.digitaldot.validator;
    exports com.digitaldot.validator.exceptions;
    requires spring.core;
    requires java.validation;
    requires org.hibernate.validator;
    requires libphonenumber;
    requires spring.context;
    requires com.fasterxml.jackson.annotation;
    requires spring.web;
}