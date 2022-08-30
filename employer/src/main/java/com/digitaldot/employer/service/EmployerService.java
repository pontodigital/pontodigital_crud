package com.digitaldot.employer.service;

import com.digitaldot.employer.mapper.EmployerMapper;
import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import com.digitaldot.employer.repository.EmployerRepository;
import com.digitaldot.employer.service.interfaces.IEmployerService;
import com.digitaldot.utils.hateos.HideLinksUtils;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class EmployerService implements IEmployerService {

    @Autowired
    private EmployerRepository repository;
    @Autowired
    private EmployerMapper employerMapper;
    @Autowired
    private HideLinksUtils hideLinksUtils;

    @Override
    public CollectionModel<EmployerDto> listAllEmployers() throws ApiException, ValidatorErrorException {

//        return employerMapper.toCollectionLinkDto(
//                employerMapper.toArrayDto(employerRepository.findAll())
//        );
        return null;
    }

    @Override
    public EmployerDto findByQueryEmployer(String query) throws ApiException, ValidatorErrorException {

        if (query.matches("[0-9]+")) {
            Optional<Employer> employer = repository.findById(Long.valueOf(query));
            if (employer.isPresent()) {
                return (EmployerDto) employerMapper.toLinkDto(
                        employerMapper.toDto(employer.get()), hideLinksUtils.hideId()
                );
            }
        }

        return (EmployerDto) employerMapper.toLinkDto(
                employerMapper.toDto(Optional.ofNullable(repository.findByDocument(query))
                        .orElseThrow( () ->new ApiException("employer not found", HttpStatus.NOT_FOUND.value()))),
                hideLinksUtils.hideId()
        );
    }

    @Override
    public EmployerDto createEmployer(EmployerDto employerDto) throws ApiException, ValidatorErrorException {

        Employer employerExists = repository.findByDocument(employerDto.getDocument());
        if (nonNull(employerExists)) {
            throw new ApiException("employee already exists", HttpStatus.BAD_REQUEST.value());
        }

        Employer employer = employerMapper.toDomain(employerDto);
        employer = repository.save(employer);

        return (EmployerDto) employerMapper.toLinkDto(employerMapper.toDto(employer));
    }

    @Override
    public EmployerUpdateDto updateEmployer(String id, EmployerUpdateDto employerUpdate) throws ApiException, ValidatorErrorException {

//        Employer employer = employerRepository.findById(id)
//                .orElseThrow( () -> new ApiException("employer not found", HttpStatus.NOT_FOUND.value()));
//
//        Optional<Employer> employerExists = Optional.ofNullable(employerRepository
//                .findByDocument(employerUpdate.getDocument())
//        );
//        if (employerExists.isPresent())
//        {
//            if (!employerExists.get().getDocument().equals(employerUpdate.getDocument()))
//            {
//                throw new ApiException("employee already exists with this document", HttpStatus.BAD_REQUEST.value());
//            }
//        }
//
//        employer.setFirstName(employerUpdate.getFirstName());
//        employer.setLastName(employerUpdate.getLastName());
//        employer.setDocument(employerUpdate.getDocument());
//        employer.setPhone(employerUpdate.getPhone());
//        employer.setType(Employer.Type.valueOf(employerUpdate.getType().name()));
//        employer.setGender(Employer.Gender.valueOf(employerUpdate.getGender().name()));
//
//        return (EmployerUpdateDto) employerMapper.toLinkDto(employerMapper.toUpdateDto(employerRepository.save(employer)),
//                hideLinksUtils.hideEdit());
        return null;
    }

    @Override
    public void deleteEmployer(String id) throws ApiException, ValidatorErrorException {
        this.findByQueryEmployer(id);
//        employerRepository.deleteById(id);
    }

//    @Transactional
//    @Override
//    public void deleteEmployerJoinUser(String id) throws ApiException, ValidatorErrorException {

//        EmployerDto employerDto = this.findByQuery(id);
//        userService.deleteUser(employerDto.getUser().getId());
//        employerRepository.deleteById(id);
//    }
}
