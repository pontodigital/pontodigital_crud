package com.digitaldot.employer.controller.v1;

import com.digitaldot.employer.controller.AbstractEmployerController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "com/digitaldot/employer/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController extends AbstractEmployerController { }
