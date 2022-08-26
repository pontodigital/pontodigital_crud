package com.digitaldot.employer.service;

import com.digitaldot.employer.mapper.EmployerMapper;
import com.digitaldot.employer.model.Employer;
import com.digitaldot.employer.model.dto.EmployerDto;
import com.digitaldot.employer.model.dto.EmployerUpdateDto;
import com.digitaldot.employer.service.interfaces.IEmployerService;
import com.digitaldot.utils.hateos.HideLinksUtils;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployerService implements IEmployerService {

//    @Autowired
//    private EmployerRepository repository;

//    @Autowired
//    private IUserService userService;

    @Autowired
    private EmployerMapper employerMapper;

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private HideLinksUtils hideLinksUtils;

    @Override
    public CollectionModel<EmployerDto> listAll() throws ApiException, ValidatorErrorException {

//        return employerMapper.toCollectionLinkDto(
//                employerMapper.toArrayDto(employerRepository.findAll())
//        );
        return null;
    }

    @Override
    public EmployerDto findByQuery(String query) throws ApiException, ValidatorErrorException {

//        Optional<Employer> employer = employerRepository.findById(query);
//        if (employer.isPresent()) {
//            return (EmployerDto) employerMapper.toLinkDto(
//                    employerMapper.toDto(employer.get()), hideLinksUtils.hideId()
//            );
//        }
//
//        return (EmployerDto) employerMapper.toLinkDto(
//                employerMapper.toDto(Optional.ofNullable(employerRepository.findByDocument(query))
//                        .orElseThrow( () ->new ApiException("employer not found", HttpStatus.NOT_FOUND.value()))),
//                hideLinksUtils.hideId()
//        );
        return null;
    }

    @Override
    public EmployerDto createJoinUser(EmployerDto employerDto) throws ApiException, ValidatorErrorException {

//        if (isNull(employerDto.getUser())) {
//            throw new ApiException("user is null", HttpStatus.NOT_FOUND.value());
//        }
//        Employer employerExists = employerRepository.findByDocument(employerDto.getDocument());
//        if (nonNull(employerExists)) {
//            throw new ApiException("employee already exists", HttpStatus.BAD_REQUEST.value());
//        }
//        employerDto.setUser(userService.createUser(employerDto.getUser()));

        Employer employer = employerMapper.toDomain(employerDto);
//        repository.save(employer);
//
//        return (EmployerDto) employerMapper.toLinkDto(employerMapper.toDto(employerDomain));
        return null;
    }

    @Override
    public EmployerUpdateDto update(String id, EmployerUpdateDto employerUpdate) throws ApiException, ValidatorErrorException {

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
    public void delete(String id) throws ApiException, ValidatorErrorException {
        this.findByQuery(id);
//        employerRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteEmployerJoinUser(String id) throws ApiException, ValidatorErrorException {

//        EmployerDto employerDto = this.findByQuery(id);
//        userService.deleteUser(employerDto.getUser().getId());
//        employerRepository.deleteById(id);
    }
}
