package com.digitaldot.employer.mapper;

import com.digitaldot.employer.controller.v1.EmployerController;
import com.digitaldot.employer.controller.v1.UserController;
import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.AbstractEmployerDto;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    public List<Employer> toArrayDomain(List<EmployerDto> listEmployerDto) {
        return listEmployerDto
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public AbstractEmployerDto toLinkDto(AbstractEmployerDto abstractEmployerDto) throws ApiException {

        abstractEmployerDto.add(linkTo(methodOn(EmployerController.class).findByQuery(abstractEmployerDto.getId()))
                .withSelfRel());
        abstractEmployerDto.add(linkTo(methodOn(EmployerController.class).findByQuery(abstractEmployerDto.getDocument()))
                .withSelfRel());

        abstractEmployerDto.add(linkTo(methodOn(EmployerController.class).listAll()).withRel(IanaLinkRelations.COLLECTION));

        if (abstractEmployerDto instanceof EmployerDto) { //link for user
            EmployerDto employerDto = (EmployerDto) abstractEmployerDto;
            employerDto.getUser().add(linkTo(methodOn(UserController.class).findByQuery(employerDto.getUser().getId()))
                    .withSelfRel());
            employerDto.getUser().add(linkTo(methodOn(UserController.class).findByQuery(employerDto.getUser().getUsername()))
                    .withSelfRel());
            employerDto.getUser().add(linkTo(methodOn(UserController.class).findByQuery(employerDto.getUser().getEmail()))
                    .withSelfRel());
            employerDto.getUser().add(linkTo(methodOn(UserController.class).listAll()).withRel(IanaLinkRelations.COLLECTION));
        }

        return abstractEmployerDto;
    }

    public CollectionModel<EmployerDto> toCollectionLinkDto(List<EmployerDto> employerDtoList) throws ApiException {

        List<EmployerDto> employerAuxList = new ArrayList<>();

        for (EmployerDto employerDto : employerDtoList) {
            employerAuxList.add((EmployerDto) toLinkDto(employerDto));
        }

        return CollectionModel.of(employerAuxList).add(linkTo(methodOn(EmployerController.class).listAll())
                .withRel(IanaLinkRelations.COLLECTION));
    }
}
