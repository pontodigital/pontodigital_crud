package com.digitaldot.employer.mapper;

import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
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

    public Employer toDomain(EmployerDto employerDto) {
        return mapper.map(employerDto, Employer.class);
    }

    public EmployerUpdateDto toUpdateDto(Employer employer) {
        return mapper.map(employer, EmployerUpdateDto.class);
    }

    public List<EmployerDto> toArrayDto(List<Employer> listEmployerDomain) {
        return listEmployerDomain
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
