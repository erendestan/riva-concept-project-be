package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean existsByUserId(long userId);

    List<UserEntity> findAllUsers();

    UserEntity save(UserEntity user);

    void deleteById(long userId);

    Optional<UserEntity> findUserById(long userId);

    int count();

    public void clear();
}
