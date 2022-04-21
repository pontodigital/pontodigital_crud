package com.pontodigital.employer.repository.employer;

import com.pontodigital.employer.model.Employer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends MongoRepository<Employer, String> {

    @Query("{ 'name' : ?0 }")
    void delete(String name);
    @Query("{ 'name' : ?0 }")
    Employer findByFirstname(String name);
    @Query("{ 'document' : ?0 }")
    Employer findByDocument(String document);
}
