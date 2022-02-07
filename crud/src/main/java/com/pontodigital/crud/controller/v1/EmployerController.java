package com.pontodigital.crud.controller.v1;

import com.pontodigital.crud.controller.AbstractEmployerController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "register/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController extends AbstractEmployerController { }
