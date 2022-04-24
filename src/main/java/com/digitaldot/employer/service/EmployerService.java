package com.digitaldot.employer.service;

import com.digitaldot.employer.exceptions.ApiException;
import com.digitaldot.employer.mapper.EmployerMapper;
import com.digitaldot.employer.mapper.UserMapper;
import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import com.digitaldot.employer.repository.employer.EmployerRepository;
import com.digitaldot.employer.service.interfaces.IEmployerService;
import com.digitaldot.employer.service.interfaces.IUserService;
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
    private EmployerRepository employerRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CollectionModel<EmployerDto> listAll() throws ApiException {

        return employerMapper.toCollectionLinkDto(
                employerMapper.toArrayDto(employerRepository.findAll())
        );
    }

    @Override
    public EmployerDto findByQuery(String query) throws ApiException {

        Optional<Employer> employer = employerRepository.findById(query);
        if (employer.isPresent()) {
            return (EmployerDto) employerMapper.toLinkDto(
                    employerMapper.toDto(employer.get())
            );
        }
        return (EmployerDto) employerMapper.toLinkDto(
                employerMapper.toDto(Optional.ofNullable(employerRepository.findByDocument(query))
                        .orElseThrow( () ->new ApiException("employer not found", HttpStatus.NOT_FOUND.value())))
        );
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

        employerDto.setUser(userService.createUser(employerDto.getUser()));

        Employer employerDomain = employerRepository.save(employerMapper.toDomain(employerDto));

        return (EmployerDto) employerMapper.toLinkDto(employerMapper.toDto(employerDomain));
    }

    @Override
    public EmployerUpdateDto update(String id, EmployerUpdateDto employerUpdate) throws ApiException {

        Employer employer = employerRepository.findById(id)
                .orElseThrow( () -> new ApiException("employer not found", HttpStatus.NOT_FOUND.value()));

        Employer employerExists = employerRepository.findByDocument(employerUpdate.getDocument());
        if (nonNull(employerExists)) {
            throw new ApiException("employee already exists", HttpStatus.BAD_REQUEST.value());
        }

        employer.setFirstName(employerUpdate.getFirstName());
        employer.setLastName(employerUpdate.getLastName());
        employer.setDocument(employerUpdate.getDocument());
        employer.setPhone(employerUpdate.getPhone());
        employer.setType(Employer.Type.valueOf(employerUpdate.getType().name()));
        employer.setGender(Employer.Gender.valueOf(employerUpdate.getGender().name()));

        return (EmployerUpdateDto) employerMapper.toLinkDto(employerMapper.toUpdateDto(employerRepository.save(employer)));
    }

    @Override
    public void delete(String id) throws ApiException {
        this.findByQuery(id);
        employerRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteEmployerJoinUser(String id) throws ApiException {

        EmployerDto employerDto = this.findByQuery(id);
        userService.deleteUser(employerDto.getUser().getId());
        employerRepository.deleteById(id);
    }
}
