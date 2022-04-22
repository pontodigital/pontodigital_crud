package com.digitaldot.employer.service;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.mapper.EmployerMapper;
import com.digitaldot.employer.mapper.UserMapper;
import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.repository.employer.EmployerRepository;
import com.digitaldot.employer.service.interfaces.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class EmployerService implements IEmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<EmployerDto> listAll() {
        return employerMapper.toArrayDto(employerRepository.findAll());
    }

    @Override
    public Employer findByName(String name) {
        return this.employerRepository.findByFirstname(name);
    }

    @Override
    public EmployerDto createJoinUser(EmployerDto employerDto) throws ApiException {

        if (isNull(employerDto.getUser())) {
            throw new ApiException("user is null", HttpStatus.NOT_FOUND.value());
        }
        Employer employerExists = employerRepository.findByDocument(employerDto.getDocument());
        if (nonNull(employerExists)) {
            throw new ApiException("employee already exists", HttpStatus.BAD_REQUEST.value());
        }

        employerDto.setUser(userService.create(employerDto.getUser()));

        Employer employerDomain = employerRepository.save(employerMapper.toDomain(employerDto));

        return employerMapper.toDto(employerDomain);
    }

    @Override
    public Employer update(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public void delete(String name) {
        this.employerRepository.deleteById(name);
    }
}
