package com.pontodigital.employer.controller;


import com.pontodigital.employer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractUserController {

    @Autowired
    private UserService userService;

//    public Response<UserDto> createJoinEmp(@RequestBody Employer employer, @RequestBody User user) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createJoinEmp())
//    }


}
