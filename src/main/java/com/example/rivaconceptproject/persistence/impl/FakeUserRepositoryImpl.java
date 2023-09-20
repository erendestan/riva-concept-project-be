package com.example.rivaconceptproject.persistence.impl;

import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {
    private static long NEXT_ID = 1;

    private final List<UserEntity> savedUsers;

    public FakeUserRepositoryImpl(){
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public boolean existsByUserId(long userId) {
        return this.savedUsers
                .stream()
                .anyMatch(userEntity -> userEntity.getId().equals(userId));
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return Collections.unmodifiableList(this.savedUsers);
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getId() == null){
            user.setId(NEXT_ID);
            NEXT_ID++;
            this.savedUsers.add(user);
        }
        return user;
    }

    @Override
    public void deleteById(long userId) {
        this.savedUsers.removeIf(userEntity -> userEntity.getId().equals(userId));
    }

    @Override
    public Optional<UserEntity> findUserById(long userId) {
        return this.savedUsers.stream()
                .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst();
    }

    @Override
    public int count() {
        return this.savedUsers.size();
    }
}
