package com.pontodigital.crud.model.dto;

public class EmployerDto {

    private String name;

    public EmployerDto() {}

    public EmployerDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
