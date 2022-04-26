package com.digitaldot.employer.model.dto;

import org.springframework.hateoas.CollectionModel;

public class PageUserDto {

    private CollectionModel<UserDto> users;
    private int itens;
    private long totalItens;
    private int totalPages;

    public CollectionModel<UserDto> getUsers() {
        return users;
    }

    public void setUsers(CollectionModel<UserDto> users) {
        this.users = users;
    }

    public int getItens() {
        return itens;
    }

    public void setItens(int itens) {
        this.itens = itens;
    }

    public long getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(long totalItens) {
        this.totalItens = totalItens;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
