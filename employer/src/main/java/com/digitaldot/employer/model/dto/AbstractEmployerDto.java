package com.digitaldot.employer.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractEmployerDto extends RepresentationModel<AbstractEmployerDto> {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String firstName;
    private String lastName;
    private String document;
    private String phone;
    private Type type;
    private Gender gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public enum Gender {
        MALE,
        FEMALE;

        Gender() {
        }
    }

    public enum Type {
        EFFECTIVE,
        INTERN;

        Type() {
        }
    }
}
