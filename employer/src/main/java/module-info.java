module employer {

    exports com.digitaldot.employer.model;
    requires validator;
    requires java.persistence;
    requires com.fasterxml.jackson.annotation;
    requires spring.hateoas;
    requires spring.context;
    requires spring.web;
    requires spring.beans;
    requires spring.tx;
    requires modelmapper;
    requires spring.data.jpa;
    requires utils;
}