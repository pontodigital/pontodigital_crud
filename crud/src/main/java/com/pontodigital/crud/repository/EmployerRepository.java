package com.pontodigital.crud.repository;

import com.github.pontodigital.model.Employer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends MongoRepository<Employer, String> {

    @Query("{ 'name' : ?0 }")
    void delete(String name);

    @Query("{ 'name' : ?0 }")
    Employer findByFirstname(String name);
}
