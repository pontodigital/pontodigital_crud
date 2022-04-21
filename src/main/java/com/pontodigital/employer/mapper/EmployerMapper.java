package com.pontodigital.employer.mapper;

import com.pontodigital.employer.model.Employer;
import com.pontodigital.employer.model.dto.EmployerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployerMapper {

    @Autowired
    ModelMapper mapper;

    public EmployerDto toDto(Employer employer) {
        return mapper.map(employer, EmployerDto.class);
    }

    public List<EmployerDto> toArrayDto(List<Employer> listEmployerDomain) {
        return listEmployerDomain
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
