package com.example.rivaconceptproject.business.impl.userusecaseimpls;

import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.entity.UserEntity;

public class UserConverter {

    private UserConverter() {
        // private constructor to hide the implicit public one
    }
    public static User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
