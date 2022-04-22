package com.digitaldot.employer.repository.employer;

import com.digitaldot.employer.model.Employer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends MongoRepository<Employer, String> {

    @Query("{ 'document' : ?0 }")
    Employer findByDocument(String document);
}
