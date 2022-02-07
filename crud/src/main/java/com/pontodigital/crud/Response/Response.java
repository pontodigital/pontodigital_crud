package com.pontodigital.crud.Response;

import java.util.List;

public class Response<T> {

    private T date;
    private List<String> erros;

    public Response(T date) {
        this.date = date;
    }

    public Response (List<String> erro){
        this.erros = erros;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
