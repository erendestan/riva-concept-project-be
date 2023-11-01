package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean existsByUserId(long userId);

    boolean existsByEmail(String email);

    List<UserEntity> findAllUsers();

    UserEntity save(UserEntity user);

    void deleteById(long userId);

    Optional<UserEntity> findUserById(long userId);
    UserEntity getId(long userId);
    int count();

    public void clear();
}
