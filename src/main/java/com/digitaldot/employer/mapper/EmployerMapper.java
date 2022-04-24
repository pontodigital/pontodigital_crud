package com.digitaldot.employer.mapper;

import com.digitaldot.employer.controller.v1.EmployerController;
import com.digitaldot.employer.controller.v1.UserController;
import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.exceptions.ValidatorErrorException;
import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.AbstractEmployerDto;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import com.digitaldot.employer.utils.HideLinksUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployerMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HideLinksUtils hideLinksUtils;

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

    public AbstractEmployerDto toLinkDto(AbstractEmployerDto abstractEmployerDto, HideLinksUtils hideLinksDto)
            throws ApiException, ValidatorErrorException {

        if (!hideLinksDto.isId())
        {
            abstractEmployerDto.add(linkTo(methodOn(EmployerController.class).findByQuery(abstractEmployerDto.getId())).withSelfRel());
        }
        if (!hideLinksDto.isEdit())
        {
            abstractEmployerDto.add(linkTo(methodOn(EmployerController.class)
                    .update(abstractEmployerDto.getId(), new EmployerUpdateDto())).withRel(IanaLinkRelations.EDIT));
        }
        if (!hideLinksDto.isDelete())
        {
            abstractEmployerDto.add(linkTo(methodOn(EmployerController.class).delete(abstractEmployerDto.getId())).withRel("delete"));
        }
        if (!hideLinksDto.isDeleteJoinUser())
        {
            abstractEmployerDto.add(linkTo(methodOn(EmployerController.class).deleteEmployerJoinUser(abstractEmployerDto.getId()))
                    .withRel("deletejoinuser"));
        }
        if (!hideLinksDto.isCollection())
        {
            abstractEmployerDto.add(linkTo(methodOn(EmployerController.class).listAll()).withRel(IanaLinkRelations.COLLECTION));
        }
        if (abstractEmployerDto instanceof EmployerDto) {

            if (!hideLinksDto.isUser())
            {
                EmployerDto employerDto = (EmployerDto) abstractEmployerDto;
                employerDto.getUser().add(linkTo(methodOn(UserController.class).findByQuery(employerDto.getUser().getId()))
                        .withSelfRel());
            }
        }

        return abstractEmployerDto;
    }

    public AbstractEmployerDto toLinkDto(AbstractEmployerDto abstractEmployerDto)
            throws ApiException, ValidatorErrorException {

        return toLinkDto(abstractEmployerDto, hideLinksUtils);
    }

    public CollectionModel<EmployerDto> toCollectionLinkDto(List<EmployerDto> employerDtoList)
            throws ApiException, ValidatorErrorException {

        for (EmployerDto employerDto : employerDtoList) {
            toLinkDto(employerDto, hideLinksUtils.hideCollection());
        }

        return CollectionModel.of(employerDtoList);
    }
}
