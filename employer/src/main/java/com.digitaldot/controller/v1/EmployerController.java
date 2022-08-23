package com.digitaldot.controller.v1;

import com.digitaldot.controller.AbstractEmployerController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "employer/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController extends AbstractEmployerController { }
