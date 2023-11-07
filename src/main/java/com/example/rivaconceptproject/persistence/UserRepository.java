package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    boolean existsByUserId(long userId);

    boolean existsByEmail(String email);

    //findAllUsers();
    List<UserEntity> findAll();

    UserEntity save(UserEntity user);

    void deleteById(long userId);

    //findByUserId
    Optional<UserEntity> findById(long userId);

    @Query("SELECT u FROM UserEntity u WHERE u.id = :userId")
    UserEntity getId(long userId);
    long count();

//    public void clear();
}
