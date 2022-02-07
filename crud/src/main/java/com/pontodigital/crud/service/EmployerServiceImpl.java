package com.pontodigital.crud.service;

import com.github.pontodigital.model.Employer;
import com.pontodigital.crud.model.dto.EmployerDto;
import com.pontodigital.crud.repository.EmployerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements IEmployerServices {


    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    MongoRepositories mongoRepositories;

    public EmployerDto findByNameDto(String name) {
        return modelMapper.map(employerRepository.findByFirstname(name), EmployerDto.class);
    }

    @Override
    public List<Employer> listAll() {
        return employerRepository.findAll();
    }

    @Override
    public Employer findByName(String name) {
        return this.employerRepository.findByFirstname(name);
    }

    @Override
    public Employer create(Employer employer) {
        return this.employerRepository.save(employer);
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
