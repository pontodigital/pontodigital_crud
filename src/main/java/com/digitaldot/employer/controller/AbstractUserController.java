package com.digitaldot.employer.controller;


import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.dto.UserDto;
import com.digitaldot.employer.service.UserService;
import com.digitaldot.employer.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class AbstractUserController {

    @Autowired
    private IUserService userService;

//    public Response<UserDto> createJoinEmp(@RequestBody Employer employer, @RequestBody User user) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createJoinEmp())
//    }

    @GetMapping("/-")
    public ResponseEntity<List<UserDto>> listAll() {
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/-/query")
    public ResponseEntity<UserDto> findByQuery(@RequestParam(name = "value") String query) throws ApiException {
        return ResponseEntity.ok(userService.findByQuery(query));
    }

}
