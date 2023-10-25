package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserConverter {

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
        //.birthDate(user.getBirthDate())
    }
}
