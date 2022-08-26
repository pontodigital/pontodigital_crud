module user {

    requires employer;
    requires utils;
    requires java.persistence;
    requires com.fasterxml.jackson.annotation;
    requires spring.hateoas;
    requires spring.beans;
    requires spring.data.commons;
    requires spring.web;
    requires spring.context;
    requires spring.data.jpa;
    requires modelmapper;
    requires validator;
}