package com.digitaldot.repository;

import com.digitaldot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("{ 'email' : ?0 }")
//    User findByEmail(String email);
//
//    @Query("{ 'username' : ?0 }")
//    User findByUsername(String username);
}
