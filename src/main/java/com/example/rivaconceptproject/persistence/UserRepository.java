package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //Derived Query (@Query is not necessary because Spring Data JPA will automatically generate the query based on the method name)
    boolean existsByEmail(String email);

    //Parameterized Query
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findByEmail(String email);

}
