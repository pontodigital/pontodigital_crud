package com.pontodigital.crud.controller;

import com.github.pontodigital.model.Employer;
import com.pontodigital.crud.Response.Response;
import com.pontodigital.crud.model.dto.EmployerDto;
import com.pontodigital.crud.service.IEmployerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEmployerController {

    @Autowired
    private IEmployerServices employerServices;

    @GetMapping
    public ResponseEntity<List<Employer>> listAll(){
        return ResponseEntity.ok(employerServices.listAll());
    }

    @GetMapping(value = "/dto")
    public ResponseEntity<Response<EmployerDto>> findByNameDto(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(new Response<>(employerServices.findByNameDto(name)));
    }

    @GetMapping(value = "/")
    public ResponseEntity<Employer> findByName (@RequestParam(name = "name") String name){
        return ResponseEntity.ok(employerServices.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Response<Employer>> create(@RequestBody Employer employer, BindingResult result){
        if (result.hasErrors()){
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            erros.forEach(System.out::println);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(erros));
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(employerServices.create(employer)));
    }

    @PutMapping(path = "/{name}")
    public ResponseEntity<Response<Employer>> update(@PathVariable (name = "name") String name, @Valid @RequestBody Employer employer, BindingResult result){
        if (result.hasErrors()){
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<>(erros));
        }

        employer.setName(name);
        return ResponseEntity.ok( new Response<>(employerServices.update(employer)));
    }

    @DeleteMapping(value = "/")
    public ResponseEntity <Response<Integer>> remover(@RequestParam(name = "name")String name){
        employerServices.delete(name);
        return ResponseEntity.ok(new Response<>(1));
    }
}
