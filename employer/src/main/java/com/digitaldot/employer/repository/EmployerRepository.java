package com.digitaldot.employer.repository;

import com.digitaldot.employer.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

//    @Query("{ 'document' : ?0 }")
//    Employer findByDocument(String document);
}
